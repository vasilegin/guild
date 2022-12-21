import Carousel from 'react-bootstrap/Carousel';
import React, {Component} from "react";

class CustomCarousel extends Component{

    constructor(props){
        super(props);
        this.images = this.props.images.map((item) => require("../../assets/" + item))
    }


    render() {
        if (this.images.length < 1){
            this.images = ["https://st.depositphotos.com/1787196/1330/i/450/depositphotos_13302306-stock-photo-furry-cute-monster.jpg"]
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