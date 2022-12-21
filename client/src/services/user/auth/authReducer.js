import { LOGIN_REQUEST, LOGOUT_REQUEST, SUCCESS, FAILURE } from "./authTypes";

const initialState = {
  id: "",
  username: "",
  isLoggedIn: "",
  role: "",
  status: ""
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case LOGIN_REQUEST:
    case LOGOUT_REQUEST:
      return {
        ...state,
      };
    case SUCCESS:
    case FAILURE:
      return {
        username: action.payload.username,
        isLoggedIn: action.payload.isLoggedIn,
        role: action.payload.role,
        status: action.payload.status,
        id: action.payload.id
      };
    default:
      return state;
  }
};

export default reducer;
