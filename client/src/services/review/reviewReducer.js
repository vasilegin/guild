import * as BT from "./reviewTypes";

const initialState = {
  review: "",
  error: "",
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case BT.SAVE_REVIEW_REQUEST:
    case BT.FETCH_REVIEW_REQUEST:
    case BT.UPDATE_REVIEW_REQUEST:
    case BT.DELETE_REVIEW_REQUEST:
      return {
        ...state,
      };
    case BT.REVIEW_SUCCESS:
      return {
        review: action.payload,
        error: "",
      };
    case BT.REVIEW_FAILURE:
      return {
        review: "",
        error: action.payload,
      };
    default:
      return state;
  }
};

export default reducer;
