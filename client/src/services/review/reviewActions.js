import * as BT from "./reviewTypes";
import axios from "axios";

export const saveReview = (review) => {
  axios
    .post("http://localhost:8081/rest/review", review)
    .then((response) => {
      console.log(response)
    })
    .catch((error) => {
      console.log(error)
    });
};

export const fetchReview = (reviewId) => {
  return (dispatch) => {
    dispatch({
      type: BT.FETCH_REVIEW_REQUEST,
    });
    axios
      .get("http://localhost:8081/rest/reviews/" + reviewId)
      .then((response) => {
        dispatch(reviewSuccess(response.data));
      })
      .catch((error) => {
        dispatch(reviewFailure(error));
      });
  };
};

export const updateReview = (review) => {
  return (dispatch) => {
    dispatch({
      type: BT.UPDATE_REVIEW_REQUEST,
    });
    axios
      .put("http://localhost:8081/rest/reviews", review)
      .then((response) => {
        dispatch(reviewSuccess(response.data));
      })
      .catch((error) => {
        dispatch(reviewFailure(error));
      });
  };
};

export const deleteReview = (reviewId) => {
  return (dispatch) => {
    dispatch({
      type: BT.DELETE_REVIEW_REQUEST,
    });
    axios
      .delete("http://localhost:8081/rest/reviews/" + reviewId)
      .then((response) => {
        dispatch(reviewSuccess(response.data));
      })
      .catch((error) => {
        dispatch(reviewFailure(error));
      });
  };
};

const reviewSuccess = (review) => {
  return {
    type: BT.REVIEW_SUCCESS,
    payload: review,
  };
};

const reviewFailure = (error) => {
  return {
    type: BT.REVIEW_FAILURE,
    payload: error,
  };
};

