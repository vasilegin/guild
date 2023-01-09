import * as BT from "./jobTypes";
import axios from "axios";

export const saveJob = (job) => {
  return (dispatch) => {
    dispatch({
      type: BT.SAVE_JOB_REQUEST,
    });
    axios
      .post("http://localhost:8081/rest/jobs", job)
      .then((response) => {
        dispatch(jobSuccess(response.data));
        console.log(response)
      })
      .catch((error) => {
        dispatch(jobFailure(error));
      });
  };
};

export const fetchJob = (jobId) => {
  return (dispatch) => {
    dispatch({
      type: BT.FETCH_JOB_REQUEST,
    });
    axios
      .get("http://localhost:8081/rest/jobs/" + jobId)
      .then((response) => {
        dispatch(jobSuccess(response.data));
      })
      .catch((error) => {
        dispatch(jobFailure(error));
      });
  };
};

export const updateJob = (job) => {
  return (dispatch) => {
    dispatch({
      type: BT.UPDATE_JOB_REQUEST,
    });
    axios
      .put("http://localhost:8081/rest/jobs", job)
      .then((response) => {
        console.log(response)
        dispatch(jobSuccess(response.data));
      })
      .catch((error) => {
        dispatch(jobFailure(error));
      });
  };
};

export const deleteJob = (jobId) => {
  return (dispatch) => {
    dispatch({
      type: BT.DELETE_JOB_REQUEST,
    });
    axios
      .delete("http://localhost:8081/rest/jobs/" + jobId)
      .then((response) => {
        dispatch(jobSuccess(response.data));
      })
      .catch((error) => {
        dispatch(jobFailure(error));
      });
  };
};

const jobSuccess = (job) => {
  return {
    type: BT.JOB_SUCCESS,
    payload: job,
  };
};

const jobFailure = (error) => {
  return {
    type: BT.JOB_FAILURE,
    payload: error,
  };
};

