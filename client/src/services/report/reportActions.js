import * as BT from "./reportTypes";
import axios from "axios";

export const saveReport = (report) => {
  axios
    .post("http://localhost:8081/rest/report", report)
    .then((response) => {
      console.log(response)
    })
    .catch((error) => {
      console.log(error)
    });
};

export const fetchReport = (reportId) => {
  return (dispatch) => {
    dispatch({
      type: BT.FETCH_REPORT_REQUEST,
    });
    axios
      .get("http://localhost:8081/rest/reports/" + reportId)
      .then((response) => {
        dispatch(reportSuccess(response.data));
      })
      .catch((error) => {
        dispatch(reportFailure(error));
      });
  };
};

export const updateReport = (report) => {
  return (dispatch) => {
    dispatch({
      type: BT.UPDATE_REPORT_REQUEST,
    });
    axios
      .put("http://localhost:8081/rest/reports", report)
      .then((response) => {
        dispatch(reportSuccess(response.data));
      })
      .catch((error) => {
        dispatch(reportFailure(error));
      });
  };
};

export const deleteReport = (reportId) => {
  return (dispatch) => {
    dispatch({
      type: BT.DELETE_REPORT_REQUEST,
    });
    axios
      .delete("http://localhost:8081/rest/reports/" + reportId)
      .then((response) => {
        dispatch(reportSuccess(response.data));
      })
      .catch((error) => {
        dispatch(reportFailure(error));
      });
  };
};

const reportSuccess = (report) => {
  return {
    type: BT.REPORT_SUCCESS,
    payload: report,
  };
};

const reportFailure = (error) => {
  return {
    type: BT.REPORT_FAILURE,
    payload: error,
  };
};

