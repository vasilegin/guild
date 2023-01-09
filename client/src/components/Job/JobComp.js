import React from "react";
import { Link } from "react-router-dom";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Corousel from "../Images/Corousel";

export const JobComp = ({job}) => {
    var photos = job.photos.map((item) => item.location)
    return (
        <div className="align-content-center content">
            <Container>
                <Row>
                    <Col md = {4}>
                        <Corousel images = {photos}/>
                    </Col>
                    <Col md = {8}>
                        <Link to={"/job/" + job.id} style={{color: 'white'}} className="nav-link h-100" preventScrollReset={true}>
                            <Row>
                                <h3>{job.title}</h3>
                            </Row>
                            <Row>
                                <h5>Rank: {job.rank}</h5>
                            </Row>
                            <Row>
                                <h5>Reward: ${job.reward}</h5>
                            </Row>
                            <Row>
                                <h5>Location: {job.location}</h5>
                            </Row>
                        </Link>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}
