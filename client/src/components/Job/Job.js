import React, { Component } from "react";
// import img from '/guild/client/src/assets/static/1.png'

import { connect } from "react-redux";
import {
  saveJob,
  fetchJob,
  updateJob,
} from "../../services/index";

import { Card, Form, Button, Col, InputGroup } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSave,
  faPlusSquare,
  faUndo,
  faList,
  faEdit,
} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";
import UploadComponent from "../Images/Upload";
import Corousel from "../Images/Corousel";

class Job extends Component {

  constructor(props) {
    super(props);
    this.state = this.initialState;
  }

  initialState = {
    id: "",
    adventurer_id: "",
    customer_id: "",
    group_id: "",
    title: "",
    description: "",
    status: "",
    rank: "",
    reward: "",
    location: "",
    dateCreated: "",
    dateAccepted: "",
    datePosted: "",
    dateResolved: "",

    show: false,
    picture: [],
    upload: {
      pictures: [],
      maxFileSize: 5242880,
      imgExtension: [".jpg", ".png"],
      defaultImages: [
      ]
    }
  };

  componentDidMount() {
    const jobId = +this.props.match.params.id;
    if (jobId) {
      this.findJobById(jobId);
    }
  }



  findJobById = (jobId) => {
    this.props.fetchJob(jobId);
    setTimeout(() => {
      let job = this.props.jobObject.job;
      console.log(job)
      var photos = job.photos.map((item) => item.location)
      console.log("1")
      if (job != null) {
        this.setState({
          id: job.id,
          title: job.title,
          description: job.description,
          status: job.status,
          rank: job.rank,
          reward: job.reward,
          location: job.location,
          dateCreated: job.dateCreated,
          dateAccepted: job.dateAccepted,
          datePosted: job.datePosted,
          dateResolved: job.dateResolved,
          picture: photos
        });
        console.log(job)
        if (job.adventurer != null){
          this.setState({adventurer_id: job.adventurer.id})
        }
        if (job.customer != null){
          this.setState({customer_id: job.customer.id})
        }
        if (job.group != null){
          this.setState({group_id: job.group.id})
        }
      }
    }, 1000);
  };

  resetJob = () => {
    this.setState(() => this.initialState);
  };

  submitJob = (event) => {
    event.preventDefault();
    console.log("2")
    console.log(this.state)
    const job = {
      id: this.state.id,
      adventurer_id: this.state.adventurer_id,
      customer_id: this.state.customer_id,
      group_id: this.state.group_id,
      title: this.state.title,
      description: this.state.description,
      status: this.state.status,
      rank: this.state.rank,
      reward: this.state.reward,
      location: this.state.location,
      dateCreated: new Date(),
      dateAccepted: this.state.dateAccepted,
      datePosted: this.state.datePosted,
      dateResolved: this.state.dateResolved
    };

    this.props.saveJob(job);
    setTimeout(() => {
      if (this.props.jobObject.job != null) {
        this.setState({ show: true, method: "post" });
        setTimeout(() => this.setState({ show: false }), 3000);
      } else {
        this.setState({ show: false });
      }
    }, 2000);
    this.setState(this.initialState);
  };

  handleChange = files => {
    const { pictures } = this.state.upload;
    console.log({ pictures, files });

    this.setState(
        {
          ...this.state,
          upload: {
            ...this.state.upload,
            pictures: [...files]
          }
        },
        () => {
          console.log("It was added!");
        }
    );
    console.log("77");
    console.log({ ...this.state});
  };

  confirmUpload = () => {
    const { pictures, defaultImages } = this.state.upload;
    console.log("Confirm Upload =>", [...pictures]);
    console.log("Confirm Upload =>", [...defaultImages]);
  };

  updateJob = (event) => {
    event.preventDefault();
    console.log("3")
    console.log(this.state)
    const job = {
      id: this.state.id,
      adventurer_id: this.state.adventurer_id,
      customer_id: this.state.customer_id,
      group_id: this.state.group_id,
      title: this.state.title,
      description: this.state.description,
      status: this.state.status,
      rank: this.state.rank,
      reward: this.state.reward,
      location: this.state.location,
      date_created: new Date(),
      date_accepted: this.state.date_accepted,
      date_posted: this.state.date_posted,
      date_resolved: this.state.date_resolved
    };
    this.props.updateJob(job);
    setTimeout(() => {
      if (this.props.jobObject.job != null) {
        this.setState({ show: true, method: "put" });
        setTimeout(() => this.setState({ show: false }), 3000);
      } else {
        this.setState({ show: false });
      }
    }, 2000);
    this.setState(this.initialState);
  };

  jobChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  jobList = () => {
    return this.props.history.push("/list");
  };


  render() {
    const { title, description, location, reward} =
      this.state;
    return (
      <div>
        <div style={{ display: this.state.show ? "block" : "none" }}>
          <MyToast
            show={this.state.show}
            message={
              this.state.method === "put"
                ? "Job Updated Successfully."
                : "Job Saved Successfully."
            }
            type={"success"}
          />
        </div>
        <Card className={"border border-dark bg-dark text-white"}>
          <Card.Header>
            <FontAwesomeIcon icon={this.state.id ? faEdit : faPlusSquare} />{" "}
            {this.state.id ? "Update Job" : "Add New Job"}
          </Card.Header>
          <Form
            onReset={this.resetJob}
            onSubmit={this.state.id ? this.updateJob : this.submitJob}
            id="bookFormId"
          >
            <Card.Body>
              <Form.Row>
                <Form.Label>Picture</Form.Label>
                {this.state.id ?
                    <div className="center-25">
                      <Corousel images = {this.state.picture}/>
                    </div> :
                    <UploadComponent
                        {...this.state.upload}
                        handleChange={this.handleChange}
                />}
              </Form.Row>
              <Form.Row>
                <Form.Group as={Col} controlId="formGridTitle">
                  <Form.Label>Title</Form.Label>
                  <Form.Control
                    required
                    autoComplete="off"
                    type="text"
                    name="Title"
                    value={title}
                    onChange={this.jobChange}
                    className={"bg-dark text-white"}
                    placeholder="Enter Job Title"
                  />
                </Form.Group>
              </Form.Row>
              <Form.Row>
                <Form.Group as={Col} controlId="formGridDescription">
                  <Form.Label>Description</Form.Label>
                  <InputGroup>
                    <Form.Control
                      required
                      as = "textarea"
                      rows ={10}
                      type="text"
                      name="Description"
                      value={description}
                      onChange={this.jobChange}
                      className={"bg-dark text-white"}
                      placeholder="Enter Job Description"
                    />
                  </InputGroup>
                </Form.Group>
              </Form.Row>
              <Form.Row>
                <Form.Group as={Col} controlId="formGridLocation">
                  <Form.Label>Location</Form.Label>
                  <Form.Control
                    required
                    autoComplete="off"
                    type="text"
                    name="Location"
                    value={location}
                    onChange={this.jobChange}
                    className={"bg-dark text-white"}
                    placeholder="Enter Job Location"
                  />
                </Form.Group>
              </Form.Row>
              <Form.Row>
                <Form.Group as={Col} controlId="formGridReward">
                  <Form.Label>Reward</Form.Label>
                  <Form.Control
                      required
                      autoComplete="off"
                      type="number"
                      name="Reward"
                      value={reward}
                      onChange={this.jobChange}
                      className={"bg-dark text-white"}
                      placeholder="Enter Job Reward"
                  />
                </Form.Group>
              </Form.Row>
            </Card.Body>
            <Card.Footer style={{ textAlign: "right" }}>
              <Button size="sm" variant="success" type="submit">
                <FontAwesomeIcon icon={faSave} />{" "}
                {this.state.id ? "Update" : "Save"}
              </Button>{" "}
              <Button size="sm" variant="info" type="reset">
                <FontAwesomeIcon icon={faUndo} /> Reset
              </Button>{" "}
              <Button
                size="sm"
                variant="info"
                type="button"
                onClick={() => this.jobList()}
              >
                <FontAwesomeIcon icon={faList} /> Job List
              </Button>{" "}
              {/*<Button*/}
              {/*    size="sm"*/}
              {/*    variant="info"*/}
              {/*    type="button"*/}
              {/*    onClick={() => this.confirmUpload()}*/}
              {/*>*/}
              {/*  <FontAwesomeIcon icon={faList} /> Confirm*/}
              {/*</Button>*/}
            </Card.Footer>
          </Form>
        </Card>
      </div>
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
    saveJob: (job) => dispatch(saveJob(job)),
    fetchJob: (jobId) => dispatch(fetchJob(jobId)),
    updateJob: (job) => dispatch(updateJob(job)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Job);
