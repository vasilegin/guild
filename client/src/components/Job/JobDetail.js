import React, { Component } from "react";

import {connect} from "react-redux";
import {
    saveJob,
    fetchJob,
    updateJob,
} from "../../services/index";

import {Card, Row, ListGroup, Button} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faDragon, faList, faSave
} from "@fortawesome/free-solid-svg-icons";
import UploadComponent from "../Images/Upload";
import Corousel from "../Images/Corousel";
import {fetchUser} from "../../services/user/adventurer/userActions";

class JobDetail extends Component {

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
        adventurerLogin: "",
        role: this.props.role,
        username: this.props.username,
        userStatus: this.props.status,
        userId: this.props.id,

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
            var photos = job.photos.map((item) => item.location)
            if (job != null) {
                this.setState({
                    id: job.id,
                    title: job.title,
                    adventurer: job.adventurer,
                    customer: job.customer,
                    group: job.group,
                    description: job.description,
                    status: job.status,
                    rank: job.rank,
                    reward: job.reward,
                    location: job.location,
                    dateCreated: job.dateCreated,
                    dateAccepted: job.dateAccepted,
                    datePosted: job.datePosted,
                    dateResolved: job.dateResolved,
                    picture: photos,
                });
                if (job.adventurer != null){
                    this.setState({
                        adventurer_id: job.adventurer.id,
                        adventurerLogin: job.adventurer.login
                    })
                }
                if (job.customer != null){
                    this.setState({
                        customer_id: job.customer.id
                    })
                }
                if (job.group != null){
                    this.setState({
                        group_id: job.group.id
                    })
                }
                console.log(this.state)
            }
        }, 1000);
    };

    jobList = () => {
        return this.props.history.push("/list");
    };

    resetJob = () => {
        this.setState(() => this.initialState);
    };

    submitJob = (event) => {
        event.preventDefault();
        // console.log("2")
        // console.log(this.state)
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
            dateCreated: this.state.dateCreated,
            dateAccepted: new Date(),
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
        // console.log("77");
        // console.log({ ...this.state});
    };

    confirmUpload = () => {
        const { pictures, defaultImages } = this.state.upload;
        // console.log("Confirm Upload =>", [...pictures]);
        // console.log("Confirm Upload =>", [...defaultImages]);
    };

    handlerAccept = () => {
        this.state.status = "Execution"
        this.props.fetchUser(this.state.userId)
        this.state.adventurer = this.props.userObject.users
        this.updateJob()
    };

    handlerRefuse = () => {
        this.setState({
            status: "Published",
            adventurer_id: ''
        })
        this.updateJob()
    };

    updateJob = () => {
        console.log("3")
        console.log(this.state)
        const job = {
            id: this.state.id,
            adventurer: this.state.adventurer,
            customer: this.state.customer,
            group: this.state.group,
            title: this.state.title,
            description: this.state.description,
            status: this.state.status,
            rank: this.state.rank,
            reward: this.state.reward,
            location: this.state.location,
            dateCreated: this.state.dateCreated,
            dateAccepted: new Date(),
            datePosted: this.state.datePosted,
            dateResolved: this.state.dateResolved
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

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    <FontAwesomeIcon icon={faDragon} />{" "}
                    Job
                </Card.Header>
                <Card.Body>
                    <Row>
                        <Card.Title>Picture</Card.Title>
                        {this.state.id ?
                            <div className="center-25">
                                <Corousel images = {this.state.picture}/>
                            </div> :
                            <UploadComponent
                                {...this.state.upload}
                                handleChange={this.handleChange}
                            />}
                    </Row>
                    <Row style={{marginTop: "20px"}}className={"margin-top: 20px"}>
                        <ListGroup className={"bg-dark text-white w-100"}>
                            <ListGroup.Item className={"border-List  bg-dark text-white w-100"}>
                                <Card.Title>Title</Card.Title>
                                <Card.Text>
                                    {this.state.title}
                                </Card.Text>
                            </ListGroup.Item>
                            <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                                <Card.Title>Description</Card.Title>
                                <Card.Text>
                                    {this.state.description}
                                </Card.Text>
                            </ListGroup.Item>
                            <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                                <Card.Title>Location</Card.Title>
                                <Card.Text>
                                    {this.state.location}
                                </Card.Text>
                            </ListGroup.Item>
                            <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                                <Card.Title>Status</Card.Title>
                                <Card.Text>
                                    {this.state.status}
                                </Card.Text>
                            </ListGroup.Item>
                            <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                                <Card.Title>Rank</Card.Title>
                                <Card.Text>
                                    {this.state.rank}
                                </Card.Text>
                            </ListGroup.Item>
                            <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                                <Card.Title>Reward</Card.Title>
                                <Card.Text>
                                    {this.state.reward}
                                </Card.Text>
                            </ListGroup.Item>
                            <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                                <Card.Title>Date Created</Card.Title>
                                <Card.Text>
                                    {this.state.dateCreated}
                                </Card.Text>
                            </ListGroup.Item>
                        </ListGroup>
                    </Row>
                </Card.Body>
                <Card.Footer style={{ textAlign: "right" }}>
                    <Button
                        hidden = {this.state.userStatus==="ADVENTURER" & (this.state.status === "Published" | this.state.status ==="Execution") ? false:true}
                        size="sm"
                        onClick={this.state.adventurerLogin!==this.state.username? () => this.handlerAccept() : () => this.handlerRefuse()}
                        variant={this.state.adventurerLogin!==this.state.username? "success" : "danger"}
                        type="submit">
                        <FontAwesomeIcon icon={faSave} />{" "}
                        {this.state.adventurerLogin!==this.state.username? "To accept" : "Refuse"}
                    </Button>{" "}
                    <Button
                        size="sm"
                        variant="info"
                        type="button"
                        onClick={() => this.jobList()}
                    >
                        <FontAwesomeIcon icon={faList} /> Job List
                    </Button>
                </Card.Footer>
            </Card>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        jobObject: state.job,
        userObject: state.user,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        saveJob: (job) => dispatch(saveJob(job)),
        fetchJob: (jobId) => dispatch(fetchJob(jobId)),
        fetchUser: (userId) => dispatch(fetchUser(userId)),
        updateJob: (job) => dispatch(updateJob(job)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(JobDetail);
