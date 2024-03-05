import {
  DISPLAY_ALERT,
  CLEAR_ALERT,
  SETUP_USER_BEGIN,
  SETUP_USER_SUCCESS,
  SETUP_USER_ERROR,
  LOGOUT_USER,
  DISPLAY_PASS_ERROR,
} from "./actions";

import { initialState } from "./appContext";

const reducer = (state, action) => {
  switch (action.type) {
    case DISPLAY_ALERT:
      return {
        ...state,
        showAlert: true,
        alert: {
          msg: action.payload.msg,
          type: action.payload.type,
        },
      };
    case CLEAR_ALERT:
      return {
        ...state,
        showAlert: false,
        alert: {
          msg: "",
          type: "",
        },
      };
    case SETUP_USER_BEGIN:
      return {
        ...state,
        isLoading: true,
      };
    case SETUP_USER_SUCCESS:
      return {
        ...state,
        isLoading: false,
        user: action.payload.user,
        jwtToken: action.payload.jwtToken,
      };
    case SETUP_USER_ERROR:
      return {
        ...state,
        isLoading: false,
        user: null,
        jwtToken: null,
      };
    case LOGOUT_USER:
      return {
        ...state,
        user: null,
        jwtToken: null,
      };
    case DISPLAY_PASS_ERROR:
      return {
        ...state,
        showAlert: true,
        alert: {
          msg: action.payload.msg,
          type: action.payload.type,
        },
      };
    default:
      return state;
  }
};

export default reducer;
