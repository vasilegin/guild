import * as BT from "./reportTypes";

const initialState = {
  report: "",
  error: "",
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case BT.SAVE_REPORT_REQUEST:
    case BT.FETCH_REPORT_REQUEST:
    case BT.UPDATE_REPORT_REQUEST:
    case BT.DELETE_REPORT_REQUEST:
      return {
        ...state,
      };
    case BT.REPORT_SUCCESS:
      return {
        report: action.payload,
        error: "",
      };
    case BT.REPORT_FAILURE:
      return {
        report: "",
        error: action.payload,
      };
    default:
      return state;
  }
};

export default reducer;
