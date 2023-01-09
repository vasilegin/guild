import React, { Component } from "react";

import { connect } from "react-redux";
import {UserComp} from "../User/UserComp";

import "../../assets/css/Style.css";
import {
  Card,
  Button,
  InputGroup,
  FormControl,
} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faList,
  faSearch,
  faTimes,
} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";
import axios from "axios";

class AdventurerList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      users: [],
      search: "",
      searchRank: "",
      searchStatus : 'TEST',
      currentPage: 1,
      usersPerPage: 10,
      sortDir: "login",
    };
  }

  sortData = () => {
    setTimeout(() => {
      this.state.sortDir === "login"
        ? this.setState({ sortDir: "id" })
        : this.setState({ sortDir: "login" });
      this.findAllAdventurer(this.state.currentPage);
    }, 500);
  };

  componentDidMount() {
    console.log("1")
    this.findAllAdventurer(this.state.currentPage);
  }

  findAllAdventurer(currentPage) {
    currentPage -= 1;
    this.state.searchStatusI = "";

    if (this.state.searchStatus !== ""){
      this.state.searchStatusI = "&status=" +
          this.state.searchStatus
    }
    axios
      .get(
        "http://localhost:8081/rest/adventurer/test?pageNumber=" +
          currentPage +
          "&pageSize=" +
          this.state.usersPerPage +
          "&sort=id&sortDir=" +
          this.state.sortDir +
          this.state.searchStatusI
      )
      .then((response) => response.data)
      .then((data) => {
        this.setState({
          users: data.content,
          totalPages: data.totalPages,
          totalElements: data.totalElements,
          currentPage: data.number + 1,
        });
      })
      .catch((error) => {
        console.log(error);
        localStorage.removeItem("jwtToken");
        this.props.history.push("/");
      });
  }

  deleteUser = (userId) => {
    this.props.deleteUser(userId);
    setTimeout(() => {
      if (this.props.userObject != null) {
        this.setState({ show: true });
        setTimeout(() => this.setState({ show: false }), 3000);
        this.findAllAdventurer(this.state.currentPage);
      } else {
        this.setState({ show: false });
      }
    }, 1000);
  };

  changePage = (event) => {
    let targetPage = parseInt(event.target.value);
    if (this.state.search) {
      this.searchData(targetPage);
    } else {
      this.findAllAdventurer(targetPage);
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
        this.findAllAdventurer(firstPage);
      }
    }
  };

  prevPage = () => {
    let prevPage = 1;
    if (this.state.currentPage > prevPage) {
      if (this.state.search) {
        this.searchData(this.state.currentPage - prevPage);
      } else {
        this.findAllAdventurer(this.state.currentPage - prevPage);
      }
    }
  };

  lastPage = () => {
    let condition = Math.ceil(
      this.state.totalElements / this.state.usersPerPage
    );
    if (this.state.currentPage < condition) {
      if (this.state.search) {
        this.searchData(condition);
      } else {
        this.findAllAdventurer(condition);
      }
    }
  };

  nextPage = () => {
    if (
      this.state.currentPage <
      Math.ceil(this.state.totalElements / this.state.usersPerPage)
    ) {
      if (this.state.search) {
        this.searchData(this.state.currentPage + 1);
      } else {
        this.findAllAdventurer(this.state.currentPage + 1);
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
    this.findAllAdventurer(this.state.currentPage);
  };

  searchData = (currentPage) => {
    currentPage -= 1;
    this.state.searchLoginI = "";
    this.state.searchRankI = "";
    this.state.searchStatusI = "";

    if (this.state.search !== ""){
      this.state.searchLoginI = "login=" +
          this.state.search + "&"
    }
    if (this.state.searchRank !== ""){
      this.state.searchRankI = "rank=" +
          this.state.searchRank + "&"
    }
    if (this.state.searchStatus !== ""){
      this.state.searchStatusI = "&status=" +
          this.state.searchStatus
    }
    axios
        .get(
            "http://localhost:8081/rest/adventurer/test" +
            "?" +
            this.state.searchLoginI +
            // this.state.searchRankI +
            this.state.searchStatusI +
            "page=" +
            currentPage +
            "&size=" +
            this.state.jobsPerPage
        )
        .then((response) => response.data)
        .then((data) => {
          this.setState({
            users: data.content,
            totalPages: data.totalPages,
            totalElements: data.totalElements,
            currentPage: data.number + 1,
          });
        });
  };

  render() {
    const { users, search, currentPage, totalPages , sortDir} = this.state;
    const userList = users.map(row=>{
        return <UserComp
            user = {row}
            rate = "TEST"
            key = {row.id}
        />
      })

    return (
        <div>
          <div style={{ display: this.state.show ? "block" : "none" }}>
            <MyToast
                show={this.state.show}
                message={"User Deleted Successfully."}
                type={"danger"}
            />
          </div>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header size="md">
              <div style={{ float: "left" }}>
                <FontAwesomeIcon icon={faList} /> Adventurer List
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
              {userList}
            </Card.Body>
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
    userObject: state.user,
  };
};

export default connect(mapStateToProps)(AdventurerList);
