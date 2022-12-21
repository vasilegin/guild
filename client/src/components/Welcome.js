import { Card } from "react-bootstrap";
import data from "../assets/text.json"

const Welcome = (props) => {

    const fill = data.map((row) => (
        <div>
            <h2 style={{borderBottom: "1px solid"}}>
                {row.title}
            </h2>
            <p>{row.text}</p>
        </div>
    ))

  return (
    <Card bg="dark" text="light">
      <Card.Header>Лор</Card.Header>
      <Card.Body style={{ overflowY: "scroll", height: "570px" }}>
          <div>{fill}</div>
      </Card.Body>
    </Card>
  );
};

export default Welcome;
