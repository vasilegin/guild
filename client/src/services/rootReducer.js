import { combineReducers } from "redux";
import userReducer from "./user/userReducer";
import authReducer from "./user/auth/authReducer";
import jobReducer from "./job/jobReducer";

const rootReducer = combineReducers({
  user: userReducer,
  job: jobReducer,
  auth: authReducer,
});

export default rootReducer;
