import * as BT from "./jobTypes";

const initialState = {
  job: "",
  error: "",
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case BT.SAVE_JOB_REQUEST:
    case BT.FETCH_JOB_REQUEST:
    case BT.UPDATE_JOB_REQUEST:
    case BT.DELETE_JOB_REQUEST:
      return {
        ...state,
      };
    case BT.JOB_SUCCESS:
      return {
        job: action.payload,
        error: "",
      };
    case BT.JOB_FAILURE:
      return {
        job: "",
        error: action.payload,
      };
    default:
      return state;
  }
};

export default reducer;
