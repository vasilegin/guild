import React from "react";

export const Review = ({review}) =>{
    return (
        <li className="correspondence__item correspondence__item--outgoing">
            <div className="correspondence__item-content">
                <div className="correspondence__item-info-group correspondence__item-info-group--left">
                    <div className="correspondence__item-main-content">
                        {review.text}
                    </div>
                </div>
            </div>
            <div className="correspondence__item-avatar">{review.author? review.author.login: "Заказчик"}</div>
        </li>
    )
}