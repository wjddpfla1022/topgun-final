import axios from "axios";
import { useCallback, useEffect, useMemo, useState } from "react"

const Payment=()=>{
   //state
   const[seatsList, setSeatsList] =useState([]);

    //effect
    useEffect(()=>{
        loadSeatsList();
    },[]);

   //callback
   const loadSeatsList= useCallback(async()=>{
        const resp = await axios.get("http://localhost:8080/seats/");
        setSeatsList(resp.data.map(seats=>{
            return{
                ...seats,
                select:false,
                qty:1
            }
        }));
   }, []);
 
   const selectSeats = useCallback((target, checked)=>{
    setSeatsList(seatsList.map(seats=>{
        if(seats.seatsNo === target.seatsNo){
            return {...seats, select:checked};
        }
        return {...seats};
    }));
   }, [seatsList]);

   const changeSeatsQty = useCallback((target, qty)=>{
    setSeatsList(seatsList.map(seats=>{
        if(seats.seatsNo===target.seatsNo){
            return {...seats, qty:qty};
        }
        return {...seats};
    }));
   }, [seatsList]);

   //memo 
   const checkedSeatsList= useMemo(()=>{
    return seatsList.filter(seats=>seats.select);
   }, [seatsList]);

   const checkedSeatsTotal = useMemo(()=>{
    return checkedSeatsList.reduce((before, current)=>{
       return before + (current.seatsPrice * current.qty);
    }, 0);
   },[checkedSeatsList]);

   const sendPurchaseRequest = useCallback(async()=>{
    if(checkedSeatsList.length===0) return;
    const resp = await axios.post(
        "http://localhost:8080/seats/purchase", 
        {
            seatsList: checkedSeatsList,
            approvalUrl: window.location.href + "/success",
            cancelUrl: window.location.href + "/cancel",
            failUrl: window.location.href + "/fail",
        }
    );
        window.sessionStorage.setItem("tid", resp.data.tid);
        window.sessionStorage.setItem("checkSeatsList", JSON.stringify(checkedSeatsList));
        window.location.href= resp.data.next_redirect_pc_url;
   }, [checkedSeatsList]);

    //view
    return(<>

        <div className="row mt-4">
            <div className="col">
                <div className="table">
                    <thead>
                        <tr>
                            <th>선택</th>
                            <th>좌석번호</th>
                            <th>등급</th>
                            <th>가격</th>
                            <th>수량</th>
                        </tr>
                    </thead>
                    <tbody>
                        {seatsList.map(seats=>(
                            <tr key={seats.seatsNo}>
                                <td>
                                    <input type="checkbox" className="form-check-input"
                                    checked={seats.select} onChange={e=>selectSeats(seats, e.target.checked)}/>
                                </td>
                                <td>{seats.seatsNo}</td>
                                <td>{seats.seatsRank}</td>
                                <td>{seats.seatsPrice}</td>
                                <td>
                                    <input type="number" className="form-control" min="1" max="5" step="1"
                                    style={{width:"100px"}} value={seats.qty}
                                    onChange={e=>changeSeatsQty(seats, e.target.value)}
                                        />
                                </td>
                        </tr>))}
                    </tbody>
                </div>
            </div>
        </div>

        <hr/>
        <div className="row mt-4">
            <div className="col">
                <h2>결제하실 금액은 {checkedSeatsTotal}원 입니다.</h2>
            </div>
        </div>
        <div className="row mt-4">
            <div className="col">
                <button className="btn btn-success w-100" 
                onClick={sendPurchaseRequest}>
                    구매하기
                </button>
            </div>
        </div>
    </>);
};
export default Payment;