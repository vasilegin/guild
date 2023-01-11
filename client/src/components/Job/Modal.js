import React, { Component } from "react";

class Modal extends Component {
    render() {
        return (
            <div
                className={`new_popup_none ${
                    this.props.isOpen ? "new_popup_appear" : null
                }`}
            >
                <div id="popupWindowWrong" className="popup"></div>
                <div id="popupBodyWrong" className="popup_body">
                    <div
                         className="popup_content border border-dark bg-dark text-white ">
                        <div
                            className="popup_close"
                            onClick={() => {
                                this.props.clickHandlerClose();
                            }}
                        >
                            X
                        </div>
                        <span>{this.props.content}</span>
                    </div>
                </div>
            </div>
        );
    }
}

export default Modal;
