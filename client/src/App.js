import React from "react";
import "./App.css";

import { Container, Row, Col } from "react-bootstrap";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";
import Job from "./components/Job/Job";
import JobList from "./components/Job/JobList";
import AdventurerList from "./components/User/AdventurerList";
import Adventurer from "./components/User/Adventurer";
import User from "./components/User/User";
import UserList from "./components/User/UserList";
import RankList from "./components/Admin/RankList";
import Register from "./components/User/Register";
import Login from "./components/User/Login";
import Footer from "./components/Footer";
import Home from "./components/Home";
import JobDetail from "./components/Job/JobDetail";
import {useSelector} from "react-redux";
import TestList from "./components/Admin/TestList";
import CardForm from "./components/Utils/Card";

const App = () => {

  const auth = useSelector((state) => state.auth);
  console.log(auth)

  window.onbeforeunload = (event) => {
    const e = event || window.event;
    e.preventDefault();
    if (e) {
      e.returnValue = "";
    }
    return "";
  };

  return (
    <Router>
      <NavigationBar />
      <Container>
        <Row>
          <Col lg={12} className={"margin-top"}>
            <Switch>
              <Route path="/" exact component={Welcome} />
              <Route path="/home" exact component={Home} />
              <Route path="/add" exact render={(props) =>
                  (<Job{...props} id={auth.id}
                                username={auth.username}/>)}/>
              <Route path="/edit/:id" exact component={Job} />
              <Route path="/job/:id" exact render={(props) =>
                  (<JobDetail{...props}
                             role={auth.role}
                             username={auth.username}
                             id={auth.id}
                             status={auth.status}
                             rank={auth.rank}/>)}
              />
              <Route path="/list" exact component={JobList} />
              <Route path="/adventurer" exact component={AdventurerList} />
              <Route path="/rank" exact component={RankList} />
              <Route path="/quiz" exact component={TestList} />
              <Route path="/user/:id" exact render={(props) =>
                  (<Adventurer{...props}
                             role={auth.role}
                             username={auth.username}
                             id={auth.id}
                             status={auth.status}/>)} />
              <Route path="/profile" exact render={(props) =>
                  (<User{...props} id={auth.id}/>)}
              />
              <Route path="/balance" exact render={(props) =>
                  (<CardForm{...props}
                             id={auth.id}/>)}/>
              <Route path="/register" exact component={Register} />
              <Route path="/login" exact component={Login} />
              <Route
                path="/logout"
                exact
                component={() => (
                  <Login message="User Logged Out Successfully." />
                )}
              />
            </Switch>
          </Col>
        </Row>
      </Container>
      <div style={{height:"50px"}}></div>
      <Footer/>
    </Router>
  );
};

export default App;
