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

class User extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
    }

    initialState = {
        id: "",
        balance: null,
        birthday: null,
        firstname: null,
        gender: null,
        login: null,
        rank: null,
        password: "",
        phone_number: null,
        surname: null,
        adventurer: null,
        guild_staff: null,
        role: null,
        change: false,

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
                    phone_number: user.phoneNumber,
                    adventurer: user.adventurer,
                    guild_staff: user.guildStaff,
                    role: user.role.name,
                    photos: photos
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
            phone_number: this.state.phone_number,
            surname: this.state.surname,
            adventurer: this.state.adventurer,
            guild_staff: this.state.guild_staff,
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
        event.preventDefault();
        console.log("3")
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
            phone_number: this.state.phone_number,
            surname: this.state.surname,
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
        this.setState(this.initialState);
    };

    userChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        });
    };


    userList = () => {
        return this.props.history.push("/adventurer");
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
                        <Col md = {4}>
                            <Card.Title className={"center-75"}>Photos</Card.Title>
                            {this.state.id ?
                                <div>
                                    <Corousel images = {this.state.picture}/>
                                </div> :
                                <UploadComponent
                                    {...this.state.upload}
                                    handleChange={this.handleChange}
                                />}
                        </Col>
                        <Col>
                            <Card
                                bg="secondary"
                                key="secondary"
                                text='white'
                                style={{ width: '18rem' }}
                                className={"border-secondary bg-dark text-white"} style={{border: "#17a2b8"}}>
                                <Card.Header>
                                    <Nav justify variant="tabs" className={"text-success"} variant="pills" defaultActiveKey="#first">
                                        <Nav.Item>
                                            <Nav.Link href="#profile">Profile</Nav.Link>
                                        </Nav.Item>
                                        <Nav.Item>
                                            <Nav.Link href="#completed">Completed tasks</Nav.Link>
                                        </Nav.Item>
                                        <Nav.Item>
                                            <Nav.Link href="#posted">Posted tasks</Nav.Link>
                                        </Nav.Item>
                                        <Nav.Item>
                                            <Nav.Link href="#statistics">Statistics</Nav.Link>
                                        </Nav.Item>
                                    </Nav>
                                </Card.Header>
                                <Card.Body>
                                    <Profile user={this.state}/>
                                </Card.Body>
                                <Card.Footer style={{ textAlign: "right" }}>
                                    {/*<Button size="sm" variant={this.state.adventurerLogin!==this.state.username? "success" : "danger"} type="submit">*/}
                                    {/*    <FontAwesomeIcon icon={faSave} />{" "}*/}
                                    {/*    {this.state.adventurerLogin!==this.state.username? "To accept" : "Refuse"}*/}
                                    {/*</Button>{" "}*/}
                                    {/*<Button*/}
                                    {/*    size="sm"*/}
                                    {/*    variant="info"*/}
                                    {/*    type="button"*/}
                                    {/*    onClick={() => this.userList()}*/}
                                    {/*>*/}
                                    {/*    <FontAwesomeIcon icon={faList} /> Adventurer List*/}
                                    {/*</Button>*/}
                                </Card.Footer>
                            </Card>
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
