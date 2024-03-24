import React, { useState, useReducer, useContext } from "react";

import reducer from "./reducer";
import axios from "axios";
import {
  DISPLAY_ALERT,
  CLEAR_ALERT,
  SETUP_USER_BEGIN,
  SETUP_USER_SUCCESS,
  SETUP_USER_ERROR,
  LOGOUT_USER,
  DISPLAY_PASS_ERROR,
} from "./actions";

const jwtToken = localStorage.getItem("jwtToken");
const role = localStorage.getItem("role");
const userId = localStorage.getItem("userId");

const initialState = {
  isLoading: false,
  showAlert: false,
  alert: {
    msg: "",
    type: "",
  },
  role: role || null,
  jwtToken: jwtToken || null,
  userId: userId || null,
};

const AppContext = React.createContext();

const AppProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const authFetch = axios.create({
    baseURL: "http://localhost:8080/api",
  });

  authFetch.interceptors.request.use(
    (config) => {
      const token = localStorage.getItem("jwtToken");
      if (token) {
        config.headers["Authorization"] = "Bearer " + token;
      }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  authFetch.interceptors.response.use(
    (response) => {
      return response;
    },
    (error) => {
      if (error.response?.status === 401) {
        dispatch({
          type: LOGOUT_USER,
        });
      }
      return Promise.reject(error);
    }
  );

  const displayAlert = (msg, type) => {
    dispatch({
      type: DISPLAY_ALERT,
      payload: {
        msg,
        type,
      },
    });
    clearAlert();
  };

  const clearAlert = () => {
    setTimeout(() => {
      dispatch({
        type: CLEAR_ALERT,
      });
    }, 3000);
  };

  const setUpUser = async (employee) => {
    dispatch({
      type: SETUP_USER_BEGIN,
    });
    try {
      await authFetch
        .post("/auth/login", employee)
        .then((res) => {
          console.log(res.data);
          dispatch({
            type: SETUP_USER_SUCCESS,
            payload: {
              role: res.data.role.toString().toLowerCase(),
              jwtToken: res.data.token,
              userId: res.data.id.toString().toLowerCase(),
            },
          });
          localStorage.setItem("jwtToken", res.data.token);
          localStorage.setItem("role", res.data.role.toString().toLowerCase());
          localStorage.setItem("userId", res.data.id.toString().toLowerCase());
          displayAlert("Login Successful", "success");
        })
        .catch((error) => {
          dispatch({
            type: SETUP_USER_ERROR,
          });
          displayAlert("Incorrect email or password", "danger");
        });
    } catch (error) {
      dispatch({
        type: SETUP_USER_ERROR,
      });
      displayAlert("Something went wrong", "danger");
    }
    clearAlert();
  };

  const logoutUser = () => {
    dispatch({
      type: LOGOUT_USER,
    });
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("role");
    localStorage.removeItem("userId");
    displayAlert("Logged out successfully", "success");
    clearAlert();
  };

  return (
    <AppContext.Provider
      value={{
        ...state,
        authFetch,
        displayAlert,
        logoutUser,
        dispatch,
        setUpUser,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};

export const useGlobalContext = () => {
  return useContext(AppContext);
};

export { AppProvider, initialState };
