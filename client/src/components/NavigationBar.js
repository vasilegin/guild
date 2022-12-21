import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Navbar, Nav, NavDropdown} from "react-bootstrap";
import { Link, HashRouter } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faUserPlus,
  faSignInAlt,
  faSignOutAlt, faUser,
} from "@fortawesome/free-solid-svg-icons";
import { logoutUser } from "../services/index";

const NavigationBar = () => {
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  const logout = () => {
    dispatch(logoutUser());
  };

  const guestLinks = (
    <>
      <div className="mr-auto"></div>
      <Nav className="navbar-right">
        <Link to={"register"} className="nav-link">
          <FontAwesomeIcon icon={faUserPlus} /> Register
        </Link>
        <Link to={"login"} className="nav-link">
          <FontAwesomeIcon icon={faSignInAlt} /> Login
        </Link>
      </Nav>
    </>
  );
  const userLinks = (
    <>
      <Nav className="mr-auto">
        <Link to={"/add"} className="nav-link">
          Add Job
        </Link>
        <Link to={"/list"} className="nav-link">
          Job List
        </Link>
        <Link to={"/adventurer"} className="nav-link">
          Adventurer List
        </Link>
        <Link to={"/rank"} className="nav-link" hidden={auth.role==="ADMIN"? false: true}>
          Job Rank
        </Link>
        <Link to={"/quiz"} className="nav-link" hidden={auth.role==="ADMIN"? false: true}>
          Adventurer Quiz
        </Link>
      </Nav>
      <Nav className="navbar-right">
        <Navbar.Collapse id="navbar-dark-example">
          <FontAwesomeIcon icon={faUser} />
          <Nav>
            <NavDropdown
                id="nav-dropdown-dark-example"
                title={auth.username}
                menuVariant="dark"
            >
              <NavDropdown.Item>
                <Link to={"/profile"} >
                  Profile
                </Link>
              </NavDropdown.Item>
              <NavDropdown.Item>
                <Link to={"/balance"} >
                  Up Balance
                </Link>
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>

        <Link to={"/logout"} className="nav-link" onClick={logout}>
          <FontAwesomeIcon icon={faSignOutAlt} /> Logout
        </Link>
      </Nav>
    </>
  );

  return (
    <Navbar bg="dark" variant="dark">
      <Link to={auth.isLoggedIn ? "home" : ""} className="navbar-brand">
        <img
          src="https://noblegarden.net/uploads/entity/thumb/thumb_13528.png"
          width="30"
          height="30"
          alt="brand"
        />{" "}
        Adventurer's Guild
      </Link>
      {auth.isLoggedIn ? userLinks : guestLinks}
    </Navbar>
  );
};

export default NavigationBar;
