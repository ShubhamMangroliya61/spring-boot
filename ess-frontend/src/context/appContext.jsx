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

// const jwtToken = sessionStorage.getItem("jwtToken");
const user = sessionStorage.getItem("user");

const initialState = {
  isLoading: false,
  showAlert: false,
  alert: {
    msg: "",
    type: "",
  },
  user: user || null,
};

const AppContext = React.createContext();

const AppProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const authFetch = axios.create({
    baseURL: "http://localhost:8080/api",
  });

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
        .post("/employee/login", employee, {
          credentials: "include",
        })
        .then((res) => {
          dispatch({
            type: SETUP_USER_SUCCESS,
            payload: {
              user: res.data,
            },
          });
          sessionStorage.setItem("user", res.data);
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
    sessionStorage.removeItem("user");
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
