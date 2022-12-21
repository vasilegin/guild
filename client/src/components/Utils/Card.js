import {Form, Button} from "react-bootstrap";
import {fetchUser, saveUser, updateUser} from "../../services/user/adventurer/userActions";
import {connect} from "react-redux";
import React, { Component } from "react";

class CardForm extends Component {

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
        money: 0,

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

    UpBalanceUser = () => {

        this.state.balance += this.state.money
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

    updateUser = () => {
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
        console.log(this.state)
    };


    userList = () => {
        return this.props.history.push("/adventurer");
    };


    render() {
        return (
            <div className={"contein"}>
                <div className="box justify-content-center align-items-center">
                    <div className="formDiv">
                        <div className="creditCard">
                            <img src={"https://s3.rncb.ru/rncb/media-files/2806/credit_v1.png"} alt={"Кредитка"}/>
                        </div>
                        <Form >
                            <Form.Group>
                                <Form.Control
                                    type="number"
                                    id="money"
                                    data-testid="money"
                                    name="money"
                                    value={this.state.money}
                                    onChange={this.userChange}
                                    placeholder="Amount Of Money"
                                />
                            </Form.Group>
                            <Button
                                size={"block"}
                                data-testid="executeButton"
                                id="executeButton"
                                type="submit"
                                onClick={() => this.UpBalanceUser()}
                            >
                                Execute
                            </Button>
                        </Form>
                    </div>
                </div>
            </div>
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

export default connect(mapStateToProps, mapDispatchToProps)(CardForm);
