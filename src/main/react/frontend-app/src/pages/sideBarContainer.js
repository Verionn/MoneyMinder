import React from "react";
import '../css/sideBarContainer.css'
import 'boxicons'
const sideBarContainer = () =>{
    return(
        <div className="sideBar">
            <a href={"https://github.com/Verionn/MoneyMinder"}>
                <h1 className="logo">MoneyMinder</h1>
            </a>
            <button>
                <p>Log in > </p>
                <p>Access your list from any device</p>
            </button>
            <ul>
                <li>
                    <box-icon type='solid' name='basket' color="white"></box-icon>
                    <h3>Shopping lists</h3>
                </li>

                <li>
                    <box-icon type='solid' name='trash' color="white"></box-icon>
                    <h3>trash</h3>
                </li>

                <li>
                    <box-icon name='question-mark' color="white"></box-icon>
                    <h3>help</h3>
                </li>

                <li>
                    <box-icon name='cog' color="white"></box-icon>
                    setting
                </li>

            </ul>

            <div className="horizontal-line"></div>
            <div className="links">
                <box-icon type='logo' size="md" name='github' color="white"></box-icon>
                <box-icon type='logo' size="md" name='facebook-circle' color="white"></box-icon>
            </div>

            <div className="terms">
                <p>Privacy Policy</p>
                <p>Terms of service</p>
            </div>

            <div className="downloads">
                <div>
                    <box-icon type='logo' name='play-store' color="gray" size="md"></box-icon>
                    get it on  <div className="boldText">Google Play</div>
                </div>
                <div>
                    <box-icon type='logo' name='apple' color="white" size="md"></box-icon>
                    get it on <div className="boldText">App Store</div>
                </div>
            </div>

            <div className="horizontal-line"></div>

            <p>2023 MoneyMinder All rights reserved</p>


        </div>
    )
}

export default sideBarContainer;