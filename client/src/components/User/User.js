import React, { Component } from "react";

import { connect } from "react-redux";
import {
    saveUser,
    fetchUser,
    updateUser,
} from "../../services/user/adventurer/userActions";

import {Card, Form, Button, Col, InputGroup, Row, Nav, ListGroup} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faPlusSquare,
    faUndo,
    faList,
    faEdit, faDragon,
} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";
import UploadComponent from "../Images/Upload";
import Corousel from "../Images/Corousel";
import {Profile} from "./Profile";
import {UserTest} from "../Utils/UserTest";
import JobListForUser from "../Job/JobListForUser";
import Gist from "../Utils/Gist";

class User extends Component {


    constructor(props) {
        super(props);
        this.state = this.initialState;
    }

    initialState = {
        id: "",
        balance: 0,
        birthday: null,
        firstname: null,
        status: null,
        gender: null,
        login: null,
        rank: null,
        password: "",
        phoneNumber: null,
        surname: null,
        adventurer: null,
        guildStaff: null,
        role: null,
        change: false,
        jobs: [],
        orders: [],
        rate: "",

        current: "PROFILE",

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
        const userId = +this.props.id;
        console.log(userId)
        if (userId) {
            this.findUserById(userId);
        }
        this.state.rate = this.props.rate
    }

    findUserById = (userId) => {
        this.props.fetchUser(userId);
        setTimeout(() => {
            let user = this.props.userObject.users;
            console.log(user)
            var photos = user.photos.map((item) => item.location)
            if (user != null) {
                this.setState({
                    id: user.id,
                    balance: user.balance,
                    birthday: user.birthday,
                    email: user.email,
                    firstname: user.firstname,
                    surname: user.surname,
                    gender: user.gender,
                    login: user.login,
                    password: user.password,
                    phoneNumber: user.phoneNumber,
                    adventurer: user.adventurer,
                    guildStaff: user.guildStaff,
                    photos: photos,
                    jobs: user.jobs,
                    orders: user.orders,
                    status: user.status,
                    role: user.role,
                });
                console.log(this.state)
            }
        }, 1000);
    };

    resetUser = () => {
        this.setState(() => this.initialState);
    };

    submitUser = (event) => {
        event.preventDefault();
        console.log("2")
        console.log(this.state)
        const user = {
            id: this.state.id,
            balance: this.state.balance,
            birthday: this.state.birthday,
            email: this.state.email,
            firstname: this.state.firstname,
            gender: this.state.gender,
            login: this.state.login,
            password: this.state.password,
            phoneNumber: this.state.phoneNumber,
            surname: this.state.surname,
            adventurer: this.state.adventurer,
            guildStaff: this.state.guildStaff,
            role: this.state.role,
        };

        this.props.saveUser(user);
        setTimeout(() => {
            if (this.props.userObject.user != null) {
                this.setState({ show: true, method: "post" });
                setTimeout(() => this.setState({ show: false }), 3000);
            } else {
                this.setState({ show: false });
            }
        }, 2000);
        this.setState(this.initialState);
    };

    UpBalanceUser = (number) => {
        this.state.balance += number
        this.updateUser()
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

    updateUser = (event) => {
        console.log("3")
        console.log(this.state)
        const user = {
            id: this.state.id,
            login: this.state.login,
            password: this.state.password,
            birthday: this.state.birthday,
            gender: this.state.gender,
            surname: this.state.surname,
            email: this.state.email,
            firstname: this.state.firstname,
            balance: this.state.balance,
            phoneNumber: this.state.phoneNumber,
            status: this.state.status,
            adventurer: this.state.adventurer,
            guildStaff: this.state.guildStaff,
            role: this.state.role,
        };
        this.props.updateUser(user);
        setTimeout(() => {
            if (this.props.userObject.user != null) {
                this.setState({ show: true, method: "put" });
                setTimeout(() => this.setState({ show: false }), 3000);
            } else {
                this.setState({ show: false });
            }
        }, 2000);
    };

    userChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        });
    };

    passTheTest = () => {
        this.state.status = "TEST"
        this.updateUser()
    }

    profileChanges = () => {
        this.state.current = "PROFILE"
        this.setState({change: true})
    }
    profileSave = () => {
        this.setState({change: false})
        this.updateUser()
    }


    userList = () => {
        return this.props.history.push("/adventurer");
    };


    render() {
        const profile = <Profile user={this.state}/>
        const posted = this.state.orders.length
        const completed = this.state.jobs.length
        console.log(posted)
        console.log(completed)
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    <FontAwesomeIcon icon={faDragon} />{" "}
                    User
                </Card.Header>
                <Card.Body>
                    <Row>
                        <Col md = {4}>
                            <Card.Title className={"center-75"}>Photos</Card.Title>
                            {this.state.status != null?
                                  <div>
                                      <Corousel images = {this.state.picture}
                                                status = {this.state.status}/>
                                  </div>:""
                            }
                            {this.state.status === "USER"?
                                <Button className={"w-100"}
                                        onClick={() => this.passTheTest()}>
                                    Пройти тестирование
                                </Button>:""
                            }
                            <p/>
                            {!this.state.change?
                                <Button className={"w-100"}
                                        onClick={() => this.profileChanges()}>
                                    Редактировать профиль
                                </Button>:
                                <Button className={"w-100 btn-success"}
                                        onClick={() => this.profileSave()}>
                                    Сохранить
                                </Button>
                            }
                        </Col>
                        <Col>
                            {this.state.role === "ADMIN" & this.state.status === "TEST"?
                                profile
                                :<Card
                                    bg="secondary"
                                    key="secondary"
                                    text='white'
                                    style={{ width: '18rem' }}
                                    className={"border-secondary bg-dark text-white"} style={{border: "#17a2b8"}}>
                                    <Card.Header>
                                        <Nav justify variant="tabs" className={"text-success"} variant="pills" defaultActiveKey="#first">
                                            <Nav.Item>
                                                <Nav.Link href="#profile" onSelect={() => this.state.current = "PROFILE"}>Profile</Nav.Link>
                                            </Nav.Item>
                                            <Nav.Item>
                                                <Nav.Link href="#current" disabled={this.state.change} onSelect={() => this.state.current = "CURRENT"}>Current tasks</Nav.Link>
                                            </Nav.Item>
                                            <Nav.Item>
                                                <Nav.Link href="#completed" disabled={this.state.change} onSelect={() => this.state.current = "COMPLETED"}>Completed tasks</Nav.Link>
                                            </Nav.Item>
                                            <Nav.Item>
                                                <Nav.Link href="#posted" disabled={this.state.change} onSelect={() => this.state.current = "POSTED"}>Posted tasks</Nav.Link>
                                            </Nav.Item>
                                            <Nav.Item>
                                                <Nav.Link href="#statistics" disabled={this.state.change} onSelect={() => this.state.current = "STATISTICS"} >Statistics</Nav.Link>
                                            </Nav.Item>
                                        </Nav>
                                    </Card.Header>
                                    <Card.Body>
                                        {this.state.current === "PROFILE"? profile: ""}
                                        {this.state.current === "CURRENT"? <JobListForUser status={"CURRENT"} id={this.state.id}/>: ""}
                                        {this.state.current === "COMPLETED"? <JobListForUser status={"COMPLETED"} id={this.state.id}/>: ""}
                                        {this.state.current === "POSTED"? <JobListForUser status={"POSTED"} id={this.state.id}/>: ""}
                                        {this.state.current === "STATISTICS"? <Gist posted={posted} completed={completed}/>: ""}
                                    </Card.Body>
                                </Card>}
                        </Col>
                    </Row>
                </Card.Body>
            </Card>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        userObject: state.user,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        saveUser: (user) => dispatch(saveUser(user)),
        fetchUser: (userId) => dispatch(fetchUser(userId)),
        updateUser: (user) => dispatch(updateUser(user)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(User);
