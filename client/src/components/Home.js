import React from "react";
import { useSelector } from "react-redux";
import authToken from "../utils/authToken";
import {Alert, Card} from "react-bootstrap";
import data from "../assets/text.json";

const Home = () => {
  if (localStorage.jwtToken) {
    authToken(localStorage.jwtToken);
  }

  const auth = useSelector((state) => state.auth);
  console.log(auth)

  const fill = data.map((row) => (
      <div>
        <h2 style={{borderBottom: "1px solid"}}>
          {row.title}
        </h2>
        <p>{row.text}</p>
      </div>
  ))

  return (
      <div>
        <Alert className="text-white text-center" style={{ backgroundColor: "#343A40", color: "#ffffff80", fontSize: "28px",   marginLeft: "auto", marginRight: "auto", display: "block"}}>
          Welcome {auth.username}
        </Alert>
        <Card bg="dark" text="light">
          <Card.Header>Лор</Card.Header>
          <Card.Body style={{ overflowY: "scroll", height: "570px" }}>
            <div>{fill}</div>
          </Card.Body>
        </Card>
      </div>
  );
};

export default Home;
