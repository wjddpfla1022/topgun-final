import { useParams } from "react-router";
import { useRecoilValue } from "recoil";
import { loginState, userState } from "../../util/recoil";
import { useCallback, useEffect, useState } from "react";
import axios from "axios";
import { useSearchParams } from "react-router-dom";

const PaymentSuccess=()=>{
    
    const {partnerOrderId} = useParams();



    const login = useRecoilValue(loginState);
    const userLoading = useRecoilValue(userState);

    //state
    const [result, setResult] = useState(null);

    //effect
    useEffect(()=>{
        if(login && userLoading){
            sendApproveRequest();
        }
    }, [login, userLoading]);
    
    //callback
    const sendApproveRequest = useCallback(async()=>{
        try {
            const resp= await axios.post("http://localhost:8080/seats/approve",
            {
                partnerOrderId: partnerOrderId,
                pgToken: new URLSearchParams(window.location.search).get("pg_token"),
                tid: window.sessionStorage.getItem("tid"),
                seatsList: JSON.parse(Window.sessionStorage.getItem("checkedSeatsList"))
            });
            setResult(true);
        }
        catch(e){
            setResult(false);
        }
        finally{
            window.sessionStorage.removeItem("tid");
            window.sessionStorage.removeItem("checkedSeatsList");
        }
    }, [login, userLoading]);

    if(result===null){
        return(<>
            <h1>결제진행중</h1>
        </>);
        }
        else if(result){
            return(<>
            <h1>partnerOrderId: {partnerOrderId}</h1>
            <h1>pg_token : {new URLSearchParams(window.location.search).get("pg_token")}</h1>
            <h1>tid : {window.sessionStorage.getItem("tid")}</h1>
            </>);
        }
        else{
            return (<>
                <h1>결제 승인 실패</h1>
            </>)
        }

};

export default PaymentSuccess;