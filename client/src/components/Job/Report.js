import React from "react";

export const Report = ({report}) =>{
    return (
        <li className="correspondence__item correspondence__item--incoming">
            <div className="correspondence__item-content">
                <div className="correspondence__item-info-group correspondence__item-info-group--left">
                    <div className="correspondence__item-main-content">
                        {report.text}
                    </div>
                </div>
            </div>
            <div className="correspondence__item-avatar">{report.author? report.author.login: "Исполнитель"}</div>
        </li>
    )
}