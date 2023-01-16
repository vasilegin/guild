import * as AT from "./authTypes";
import axios from "axios";

const AUTH_URL = "http://localhost:8081/rest/user/authenticate";

export const authenticateUser = (login, password) => async (dispatch) => {
  dispatch(loginRequest());
  try {
    password = "{noop}" + password
    console.log(login, password, AUTH_URL);
    const response = await axios.post(AUTH_URL, {
      login: login,
      password: password,
    });
    console.log(response.data.token);
    localStorage.setItem("jwtToken", response.data.token);
    dispatch(success({ username: response.data.name, isLoggedIn: true, role: response.data.role, id: response.data.id, status: response.data.status, rank: response.data.rank}));
    return Promise.resolve(response.data);
  } catch (error) {
    dispatch(failure());
    return Promise.reject(error);
  }
};

export const logoutUser = () => {
  return (dispatch) => {
    dispatch(logoutRequest());
    localStorage.removeItem("jwtToken");
    dispatch(success({response:"", username: "", isLoggedIn: false, role: "USER", id: "", status: "", rank: ""}));
  };
};

const loginRequest = () => {
  return {
    type: AT.LOGIN_REQUEST,
  };
};

const logoutRequest = () => {
  return {
    type: AT.LOGOUT_REQUEST,
  };
};

const success = (isLoggedIn) => {
  return {
    type: AT.SUCCESS,
    payload: isLoggedIn,
  };
};

const failure = () => {
  return {
    type: AT.FAILURE,
    payload: false,
  };
};
