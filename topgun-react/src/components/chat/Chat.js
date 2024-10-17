import { useState } from "react";
import './Chat.css'; // CSS 파일 임포트
import { useRecoilValue } from "recoil";
import { loginState } from "../../util/recoil";
import axios from "axios";

const Chat = () => {
    //state
    const [input, setInput] = useState("");
    const [messageList, setMessageList] = useState([]);
    
    const [client, setClient] = useState(null);
    const [connect, setConnect] = useState(false);

    //recoil   

    //token
    const accessToken = axios.defaults.headers.common["Authorization"];
    const refreshToken = window.localStorage.getItem("refreshToken") 
                                    || window.sessionStorage.getItem("refreshToken");

    return (<>
        <div className="row mt-4">
            <div className="col">
                <div className="chat-container mt-3">
                    <ul className="list-group">
                        <li className="list-group-item">

                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </>);
};

export default Chat;