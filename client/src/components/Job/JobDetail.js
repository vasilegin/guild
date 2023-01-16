import React, { Component } from "react";

import {connect} from "react-redux";
import {
    saveJob,
    fetchJob,
    updateJob,
} from "../../services/index";

import {Card, Row, ListGroup, Button, Col, Form, Nav, InputGroup} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faDragon, faEdit, faLaptop, faList, faSave
} from "@fortawesome/free-solid-svg-icons";
import UploadComponent from "../Images/Upload";
import Corousel from "../Images/Corousel";
import {fetchUser} from "../../services/user/adventurer/userActions";
import Modal from "./Modal";
import {saveReport} from "../../services/report/reportActions";
import {saveReview} from "../../services/review/reviewActions";
import {Review} from "./Review";
import {Report} from "./Report";
import {JobComp} from "./JobComp";

class JobDetail extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
    }



    initialState = {
        id: "",
        adventurerId: "",
        customerId: "",
        groupId: "",
        title: "",
        description: "",
        status: "",
        rank: "F",
        report: [],
        review: [],
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
        userRank: this.props.rank,
        current: "JOB",
        isReport: false,
        isReview: false,
        reportText: "",
        reviewText: "",
        Rank: {
                "F" : 0,
                "E" : 1,
                "D" :  2,
                "B" : 3,
                "A" : 4,
                "A+" : 5,
                "A++" : 6,
                "S" : 7,
                "SS" : 8
                },

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
            var photos = []
            if (job.photos.length > 0){
                var photos = job.photos.map((item) => item.location)
            }
            console.log(job)
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
                    report: job.reports,
                    review: job.reviews,
                });
                if (job.adventurer != null){
                    this.setState({
                        adventurerId: job.adventurer.id,
                        adventurerLogin: job.adventurer.login
                    })
                }
                if (job.customer != null){
                    this.setState({
                        customerId: job.customer.id
                    })
                }
                if (job.group != null){
                    this.setState({
                        groupId: job.group.id
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
        // event.preventDefault();
        // console.log("2")
        // console.log(this.state)
        const job = {
            id: this.state.id,
            adventurerId: this.state.adventurerId,
            customerId: this.state.customerId,
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


    handlerAccept = () => {
        this.state.status = "Execution"
//        this.props.fetchUser()
        this.state.adventurerId = this.state.userId
//        this.state.adventurer = this.props.userObject.users
        console.log(this.state)
        this.updateJob()
    };

    handlerRefuse = () => {
        this.state.status= "Published"
        this.state.adventurerId= null
        this.state.adventurer= null
        this.updateJob()
    };

    updateJob = () => {
        console.log("3")
        console.log(this.state)
        const job = {
            id: this.state.id,
            // adventurer: this.state.adventurer,
            // customer: this.state.customer,
            adventurerId: this.state.adventurerId,
            customerId: this.state.customerId,
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

    estimate = () => {
        this.state.status = "Published"
        this.state.datePosted = new Date()
        this.updateJob()
    }

    openModalReport = () => {
        this.state.isReport = true
        console.log(this.state)
    };

    openModalReview = () => this.state.isReview = true;

    clickHandlerClose = () => {
        this.setState({
            isReport: false,
            isReview: false,
        });
    };

    saveReport = () => {
        const report = {
            text: this.state.reportText,
            jobId: this.state.id,
            dateCreated: new Date(),
            authorId: this.state.userId,
//            author: this.state.adventurer
        }
        saveReport(report)
        this.state.report.push(report)
        this.clickHandlerClose()
    }

    saveReview= () => {
        const review = {
            text: this.state.reviewText,
            jobId: this.state.id,
            dateCreated: new Date(),
//            author: this.state.customer,
            authorId: this.state.userId,
            score: 0
        }
        saveReview(review)
        this.state.review.push(review)
        this.clickHandlerClose()
    }

    acceptReview= () => {
        const review = {
            text: this.state.reviewText,
            jobId: this.state.id,
            dateCreated: new Date(),
//            author: this.state.customer,
            authorId: this.state.userId,
            score: 100,
        }
        saveReview(review)
        this.state.status = "Completed"
        this.state.review.push(review)
        this.clickHandlerClose()
    }



    edit = () => {
        return this.props.history.push("/edit/" + this.state.id);
    }

    jobChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        });
    };

    render(){
        console.log((this.state.Rank[this.state.rank] - this.state.Rank[this.state.userRank]) > 1)
        const report =
            <div>
                {/*<UploadComponent*/}
                {/*    {...this.state.upload}*/}
                {/*    handleChange={this.handleChange}*/}
                {/*/>*/}
                <Form>
                    <Form.Row>
                        <Form.Group as={Col} controlId="formGridMessage">
                            <Form.Label>Message</Form.Label>
                            <InputGroup>
                                <Form.Control
                                    required
                                    as = "textarea"
                                    rows ={10}
                                    type="text"
                                    name="reportText"
                                    value={this.state.reportText}
                                    onChange={this.jobChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter Report Text"
                                />
                            </InputGroup>
                        </Form.Group>
                    </Form.Row>
                </Form>
                <Button
                    size="sm"
                    variant="info"
                    type="submit"
                    onClick={() => this.saveReport()}
                >
                    <FontAwesomeIcon icon={faSave} /> Send
                </Button>
            </div>

        const review =
            <div>
                {/*<UploadComponent*/}
                {/*    {...this.state.upload}*/}
                {/*    handleChange={this.handleChange}*/}
                {/*/>*/}
                <Form>
                    <Form.Row>
                        <Form.Group as={Col} controlId="formGridMessage">
                            <Form.Label>Message</Form.Label>
                            <InputGroup>
                                <Form.Control
                                    required
                                    as = "textarea"
                                    rows ={10}
                                    type="text"
                                    name="reviewText"
                                    value={this.state.reviewText}
                                    onChange={this.jobChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter Review Text"
                                />
                            </InputGroup>
                        </Form.Group>
                    </Form.Row>
                </Form>
                <Button
                    size="sm"
                    variant="info"
                    type="button"
                    onClick={() => this.saveReview()}
                >
                    <FontAwesomeIcon icon={faSave} /> Send
                </Button>{" "}
                <Button
                    size="sm"
                    variant="success"
                    type="button"
                    onClick={() => this.acceptReview()}
                >
                    <FontAwesomeIcon icon={faSave} />
                    To accept
                </Button>
            </div>

        const job =
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
                    {this.state.status === "Created" & this.state.role === "ADMIN"?
                        <Form.Row className={"text-success bg-dark"}>
                            <Form.Group as={Col} controlId="formGridRank">
                                <Form.Label>Rank</Form.Label>
                                <Form.Control as="select"
                                              required
                                              name="rank"
                                              value={this.state.rank}
                                              onChange={this.jobChange}
                                >
                                    <option value="F">F</option>
                                    <option value="E">E</option>
                                    <option value="D">D</option>
                                    <option value="B">B</option>
                                    <option value="A">A</option>
                                    <option value="A+">A+</option>
                                    <option value="A++">A++</option>
                                    <option value="S">S</option>
                                    <option value="SS">SS</option>
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>:
                        <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                            <Card.Title>Rank</Card.Title>
                            <Card.Text>
                                {this.state.rank}
                            </Card.Text>
                        </ListGroup.Item>
                    }
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

        const all = [].concat(this.state.report, this.state.review).sort((a, b) => Date.parse(a.dateCreated) - Date.parse(b.dateCreated))
        const RR = all.map(R => {
            if (R){
                    if (R.hasOwnProperty("score")) {
                        return <Review review={R}
                                       key = {R.id}/>
                    } else {
                        return <Report report={R}
                                       key = {R.id}/>
                    }
                }})




        const messages =
            <Card.Body>
                <ul className="expander__content correspondence__group-items">
                    {RR}
                </ul>
            </Card.Body>

        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    <Nav justify variant="tabs" className={"text-white text-success border-white"} variant="pills" defaultActiveKey="#first">
                        <Nav.Item>
                            <Nav.Link className={"text-white border-white"} href="#job" onSelect={() => this.state.current = "JOB"}>
                                <FontAwesomeIcon icon={faDragon} />{" "}
                                Job
                            </Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link className={"text-white border-white"}  href="#messages" onSelect={() => this.state.current = "MESSAGES"}>
                                <FontAwesomeIcon icon={faLaptop} />{" "}
                                Messages
                            </Nav.Link>
                        </Nav.Item>
                    </Nav>
                </Card.Header>

                <Modal
                    content={report}
                    isOpen={this.state.isReport}
                    clickHandlerClose={this.clickHandlerClose}
                />

                <Modal
                    content={review}
                    isOpen={this.state.isReview}
                    clickHandlerClose={this.clickHandlerClose}
                />

                {this.state.current === "JOB"? job: ""}
                {this.state.current === "MESSAGES"? messages: ""}
                <Card.Footer style={{ textAlign: "right" }}>
                    {this.state.adventurerId === this.state.userId &
                     this.state.status === "Execution"?
                        <Nav.Link
                            style = {{display: 'inline-block', padding: '0px'}}
                            className={"text-white border-white float-left "} href="#report">
                            <Button
                                size="sm"
                                variant="secondary"
                                type="button"
                                onClick={() => this.openModalReport()}
                                >
                                Report
                            </Button>
                        </Nav.Link>: ""}{" "}
                    {this.state.customerId === this.state.userId &
                     this.state.status === "Execution"?
                        <Nav.Link
                            style = {{display: 'inline-block', padding: '0px'}}
                            className={"text-white border-white float-left "} href="#report">
                            <Button
                                size="sm"
                                variant="secondary"
                                type="button"
                                onClick={() => this.openModalReview()}
                            >
                                Review
                            </Button>
                        </Nav.Link>: ""}{" "}


                    {this.state.status === "Created" & this.state.role === "ADMIN"?
                        <Button
                            size="sm"
                            variant="success"
                            type="button"
                            onClick={() => this.estimate()}>
                            <FontAwesomeIcon icon={faSave} />{" "}
                            Rate
                        </Button> :""}{" "}
                    {this.state.status === "Created" & this.state.customerId === this.state.userId?
                        <Button
                            size="sm"
                            variant="info"
                            type="button"
                            onClick={() => this.edit()}>
                            <FontAwesomeIcon icon={faEdit} />{" "}
                            Edit
                        </Button> :""}{" "}
                    <Button
                        hidden = {(this.state.Rank[this.state.rank] - this.state.Rank[this.state.userRank]) <= 1 & this.state.customerId !== this.state.userId & this.state.userStatus==="ADVENTURER" & (this.state.status === "Published" | this.state.status ==="Execution") ? false:true}
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
