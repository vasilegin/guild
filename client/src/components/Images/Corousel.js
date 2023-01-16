import Carousel from 'react-bootstrap/Carousel';
import React, {Component} from "react";

class CustomCarousel extends Component{

    constructor(props){
        super(props);
        this.images = this.props.images.map((item) => require("../../assets/" + item))
        this.status = this.props.status
    }


    render() {
    console.log(this.status)
        if (this.images.length < 1){
            this.images = ["https://www.pngmart.com/files/4/Blue-Monster-PNG-Free-Download.png"]
        }
        if (this.status == "USER"){
            this.images = ["https://www.pngplay.com/wp-content/uploads/12/User-Avatar-Profile-PNG-Free-File-Download.png"]
        }
        if (this.status == "ADVENTURER"){
            this.images = ["https://cdn-icons-png.flaticon.com/512/3903/3903390.png"]
        }
        if (this.status == "TEST"){
            this.images = ["https://www.pngplay.com/wp-content/uploads/12/User-Avatar-Profile-PNG-Free-File-Download.png"]
        }

        const carousel = this.images.map(image=>{
            return  <Carousel.Item>
                        <img
                            className="d-block w-100 align-content-center carousel"
                            src={image}
                            alt="Картинка"
                        />
                    </Carousel.Item>
        })
        return (
            <Carousel slide={false} className="center-75">
                {carousel}
            </Carousel>
        );
    }
}

export default CustomCarousel;


// <div style={{height:"100px", width:"100px"}}>
//     <CustomCarousel images={["images/Igor/Job/1.jpg", "images/Igor/Job/2.jpg"]}/>
// </div>