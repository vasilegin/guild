import React, { Component } from "react";

import { connect } from "react-redux";
import { deleteJob } from "../../services/index";
import {JobComp} from "./JobComp";

import "./../../assets/css/Style.css";
import {
  Card,
  Table,
  Image,
  ButtonGroup,
  Form,
  Button,
  InputGroup,
  FormControl,
} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faList,
  faEdit,
  faTrash,
  faStepBackward,
  faFastBackward,
  faStepForward,
  faFastForward,
  faSearch,
  faTimes,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import MyToast from "../MyToast";
import axios from "axios";

class JobList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      jobs: [],
      search: "",
      searchTitle : '',
      searchStatus : 'Published',
      searchRank : '',
      currentPage: 1,
      jobsPerPage: 5,
      sortDir: "title",
      userId: this.props.id,
      status: this.props.status,
    };
    console.log(this.state)
  }

  sortData = () => {
    setTimeout(() => {
      this.state.sortDir === "title"
        ? this.setState({ sortDir: "id" })
        : this.setState({ sortDir: "title" });
      this.findAllJobs(this.state.currentPage);
    }, 500);
  };

  componentDidMount() {
    this.findAllJobs(this.state.currentPage);
  }

  findAllJobs(currentPage) {
    currentPage -= 1;
    this.state.searchStatusI = "";
    this.request = ""
    if (this.state.searchStatus !== ""){
      this.state.searchStatusI = "&status=" +
          this.state.searchStatus
    }
    if (this.state.status === "COMPLETED"){
      this.request = "http://localhost:8081/rest/jobs/adventurer?page=" +
          currentPage +
          "&size=" +
          this.state.jobsPerPage +
          "&status=Completed"+
          "&id=" + this.state.userId
    }
    if (this.state.status === "POSTED"){
      this.request = "http://localhost:8081/rest/jobs/customer?page=" +
          currentPage +
          "&size=" +
          this.state.jobsPerPage +
          "&id=" + this.state.userId
    }
    axios
      .get(this.request)
      .then((response) => response.data)
      .then((data) => {
        this.setState({
          jobs: data.content,
          totalPages: data.totalPages,
          totalElements: data.totalElements,
          currentPage: data.number + 1,
        });
      })
      .catch((error) => {
        localStorage.removeItem("jwtToken");
        this.props.history.push("/");
      });
  }

  deleteJob = (jobId) => {
    this.props.deleteJob(jobId);
    setTimeout(() => {
      if (this.props.jobObject != null) {
        this.setState({ show: true });
        setTimeout(() => this.setState({ show: false }), 3000);
        this.findAllJobs(this.state.currentPage);
      } else {
        this.setState({ show: false });
      }
    }, 1000);
  };

  changePage = (event) => {
    console.log("1")
    let targetPage = parseInt(event.target.value);
    if (this.state.search) {
      this.searchData(targetPage);
    } else {
      this.findAllJobs(targetPage);
    }
    this.setState({
      [event.target.name]: targetPage,
    });
  };

  firstPage = () => {
    let firstPage = 1;
    if (this.state.currentPage > firstPage) {
      if (this.state.search) {
        this.searchData(firstPage);
      } else {
        this.findAllJobs(firstPage);
      }
    }
  };

  prevPage = () => {
    let prevPage = 1;
    if (this.state.currentPage > prevPage) {
      if (this.state.search) {
        this.searchData(this.state.currentPage - prevPage);
      } else {
        this.findAllJobs(this.state.currentPage - prevPage);
      }
    }
  };

  lastPage = () => {
    let condition = Math.ceil(
      this.state.totalElements / this.state.jobsPerPage
    );
    if (this.state.currentPage < condition) {
      if (this.state.search) {
        this.searchData(condition);
      } else {
        this.findAllJobs(condition);
      }
    }
  };

  nextPage = () => {
    console.log(this.state.search)
    if (
      this.state.currentPage <
      Math.ceil(this.state.totalElements / this.state.jobsPerPage)
    ) {
      if (this.state.search) {
        this.searchData(this.state.currentPage + 1);
      } else {
        this.findAllJobs(this.state.currentPage + 1);
      }
    }
  };

  searchChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  cancelSearch = () => {
    this.setState({ search: "" });
    this.findAllJobs(this.state.currentPage);
  };

  searchData = (currentPage) => {
    currentPage -= 1;
    console.log(this.state.currentPage)
    console.log(currentPage)
    this.state.searchTitleI = "";
    this.state.searchStatusI = "";
    this.state.searchRankI = "";

    if (this.state.search !== ""){
      this.state.searchTitleI = "title=" +
      this.state.search + "&"
    }
    if (this.state.searchStatus !== ""){
      this.state.searchStatusI = "status=" +
      this.state.searchStatus + "&"
    }
    if (this.state.searchRank !== ""){
      this.state.searchRankI = "rank=" +
      this.state.searchRank + "&"
    }
    axios
      .get(
        "http://localhost:8081/rest/jobs/search" +
          "?" +
          this.state.searchTitleI +
          this.state.searchStatusI +
          this.state.searchRankI +
          "page=" +
          currentPage +
          "&size=" +
          this.state.jobsPerPage
      )
      .then((response) => response.data)
      .then((data) => {
        this.setState({
          jobs: data.content,
          totalPages: data.totalPages,
          totalElements: data.totalElements,
          currentPage: data.number + 1,
        });
      });
  };

  render() {
    const { jobs, search, currentPage, totalPages , sortDir} = this.state;
    const jobList = jobs.map(row=>{
        return <JobComp
            job = {row}
            key = {row.id}
        />
      })

    return (
        <div>
          <div style={{ display: this.state.show ? "block" : "none" }}>
            <MyToast
                show={this.state.show}
                message={"Job Deleted Successfully."}
                type={"danger"}
            />
          </div>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header size="md">
              <div style={{ float: "left" }}>
                <FontAwesomeIcon icon={faList} /> {this.state.status}
              </div>
              <div style={{ float: "right" }}>
                <InputGroup size="md">
                  <FormControl
                      placeholder="Search"
                      name="search"
                      value={search}
                      className={"info-border bg-dark text-white"}
                      onChange={this.searchChange}
                  />
                  <InputGroup.Append>
                    <Button
                        size="sm"
                        variant="outline-info"
                        type="button"
                        onClick={this.searchData}
                    >
                      <FontAwesomeIcon icon={faSearch} />
                    </Button>
                    <Button
                        size="sm"
                        variant="outline-danger"
                        type="button"
                        onClick={this.cancelSearch}
                    >
                      <FontAwesomeIcon icon={faTimes} />
                    </Button>
                  </InputGroup.Append>
                </InputGroup>
              </div>
            </Card.Header>
            <Card.Body>
              {jobList}
            </Card.Body>
            {jobs.length > 1 ? (
                  <Card.Footer>
                    <div style={{ float: "left" }}>
                      Showing Page {this.state.currentPage} of {this.state.totalPages}
                    </div>
                    <div style={{ float: "right" }}>
                      <InputGroup size="sm">
                        <InputGroup.Prepend>
                          <Button
                            type="button"
                            variant="outline-info"
                            disabled={this.state.currentPage === 1}
                            onClick={this.firstPage}
                          >
                            <FontAwesomeIcon icon={faFastBackward} /> First
                          </Button>
                          <Button
                            type="button"
                            variant="outline-info"
                            disabled={this.state.currentPage === 1}
                            onClick={this.prevPage}
                          >
                            <FontAwesomeIcon icon={faStepBackward} /> Prev
                          </Button>
                        </InputGroup.Prepend>
                        <FormControl
                          className={"page-num bg-dark"}
                          name="currentPage"
                          value={currentPage}
                          onChange={this.changePage}
                        />
                        <InputGroup.Append>
                          <Button
                            type="button"
                            variant="outline-info"
                            disabled={this.state.currentPage === totalPages}
                            onClick={this.nextPage}
                          >
                            <FontAwesomeIcon icon={faStepForward} /> Next
                          </Button>
                          <Button
                            type="button"
                            variant="outline-info"
                            disabled={this.state.currentPage === totalPages}
                            onClick={this.lastPage}
                          >
                            <FontAwesomeIcon icon={faFastForward} /> Last
                          </Button>
                        </InputGroup.Append>
                      </InputGroup>
                    </div>
                  </Card.Footer>
                ) : null}
          </Card>
        </div>

      //     <Card.Body>
      //       <Table bordered hover striped variant="dark">
      //         <thead>
      //           <tr>
      //             <th>Title</th>
      //             <th>Author</th>
      //             <th>ISBN Number</th>
      //             <th onClick={this.sortData}>
      //               Price{" "}
      //               <div
      //                 className={
      //                   this.state.sortDir === "asc"
      //                     ? "arrow arrow-up"
      //                     : "arrow arrow-down"
      //                 }
      //               >
      //                 {" "}
      //               </div>
      //             </th>
      //             <th>Language</th>
      //             <th>Genre</th>
      //             <th>Actions</th>
      //           </tr>
      //         </thead>
      //         <tbody>
      //           {jobs.length === 0 ? (
      //             <tr align="center">
      //               <td colSpan="7">No Books Available.</td>
      //             </tr>
      //           ) : (
      //             jobs.map((job) => (
      //               <tr key={job.id}>
      //                 <td>
      //                   <Image
      //                     src={job.coverPhotoURL}
      //                     roundedCircle
      //                     width="25"
      //                     height="25"
      //                   />{" "}
      //                   {job.title}
      //                 </td>
      //                 <td>{job.author}</td>
      //                 <td>{job.isbnNumber}</td>
      //                 <td>{job.price}</td>
      //                 <td>{job.language}</td>
      //                 <td>{job.genre}</td>
      //                 <td>
      //                   <ButtonGroup>
      //                     <Link
      //                       to={"edit/" + job.id}
      //                       className="btn btn-sm btn-outline-primary"
      //                     >
      //                       <FontAwesomeIcon icon={faEdit} />
      //                     </Link>{" "}
      //                     <Button
      //                       size="sm"
      //                       variant="outline-danger"
      //                       onClick={() => this.deleteJob(job.id)}
      //                     >
      //                       <FontAwesomeIcon icon={faTrash} />
      //                     </Button>
      //                   </ButtonGroup>
      //                 </td>
      //               </tr>
      //             ))
      //           )}
      //         </tbody>
      //       </Table>
      //     </Card.Body>
      //     {jobs.length > 0 ? (
      //       <Card.Footer>
      //         <div style={{ float: "left" }}>
      //           Showing Page {currentPage} of {totalPages}
      //         </div>
      //         <div style={{ float: "right" }}>
      //           <InputGroup size="sm">
      //             <InputGroup.Prepend>
      //               <Button
      //                 type="button"
      //                 variant="outline-info"
      //                 disabled={currentPage === 1 ? true : false}
      //                 onClick={this.firstPage}
      //               >
      //                 <FontAwesomeIcon icon={faFastBackward} /> First
      //               </Button>
      //               <Button
      //                 type="button"
      //                 variant="outline-info"
      //                 disabled={currentPage === 1 ? true : false}
      //                 onClick={this.prevPage}
      //               >
      //                 <FontAwesomeIcon icon={faStepBackward} /> Prev
      //               </Button>
      //             </InputGroup.Prepend>
      //             <FormControl
      //               className={"page-num bg-dark"}
      //               name="currentPage"
      //               value={currentPage}
      //               onChange={this.changePage}
      //             />
      //             <InputGroup.Append>
      //               <Button
      //                 type="button"
      //                 variant="outline-info"
      //                 disabled={currentPage === totalPages ? true : false}
      //                 onClick={this.nextPage}
      //               >
      //                 <FontAwesomeIcon icon={faStepForward} /> Next
      //               </Button>
      //               <Button
      //                 type="button"
      //                 variant="outline-info"
      //                 disabled={currentPage === totalPages ? true : false}
      //                 onClick={this.lastPage}
      //               >
      //                 <FontAwesomeIcon icon={faFastForward} /> Last
      //               </Button>
      //             </InputGroup.Append>
      //           </InputGroup>
      //         </div>
      //       </Card.Footer>
      //     ) : null}
      //   </Card>
      // </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    jobObject: state.job,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    deleteJob: (jobId) => dispatch(deleteJob(jobId)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(JobList);
