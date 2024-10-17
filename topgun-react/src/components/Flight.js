import { useCallback, useState, useEffect } from "react";
import axios from "axios";
import { FaTrash } from "react-icons/fa";

const Flight = () => {
    const [flightList, setFlightList] = useState([]);
    const [input, setInput] = useState({
        flightNumber: "",
        departureAirport: "",
        arrivalAirport: "",
        departureTime: "",
        arrivalTime: "",
        flightTotalSeat: "",
        flightStatus: "대기", // Default status
    });

    useEffect(() => {
        loadList();
    }, []);

    const loadList = useCallback(() => {
        axios.post("http://localhost:8080/flight/")
            .then(resp => {
                setFlightList(resp.data);
            })
            .catch(err => {
                console.error("Failed to load flight list:", err);
            });
    }, []);

    const deleteFlight = useCallback((target) => {
        const choice = window.confirm("정말 삭제하시겠습니까?");
        if (choice) {
            axios.delete(`/flight/${target.flight_id}`)
                .then(() => {
                    loadList(); // Refresh the list
                })
                .catch(err => {
                    console.error("Failed to delete flight:", err);
                });
        }
    }, [loadList]);

    const changeInput = useCallback(e => {
        const { name, value } = e.target;
        setInput(prevInput => ({
            ...prevInput,
            [name]: value
        }));
    }, []);

    const addInput = useCallback(() => {
        axios.post("/flight/", input)
            .then(() => {
                clearInput(); // Clear input fields
                loadList(); // Reload the list
            })
            .catch(err => {
                console.error("Failed to add flight:", err);
            });
    }, [input, loadList]);

    const clearInput = useCallback(() => {
        setInput({
            flightNumber: "",
            departureAirport: "",
            arrivalAirport: "",
            departureTime: "",
            arrivalTime: "",
            flightTotalSeat: "",
            flightStatus: "대기", // Reset to default
        });
    }, []);

    return (
        <>
            <div className="row mt-4">
                <div className="col">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>항공편 번호</th>
                                <th>출발 공항</th>
                                <th>도착 공항</th>
                                <th>출발 시간</th>
                                <th>도착 시간</th>
                                <th>총 좌석 수</th>
                                <th>상태</th> {/* Added column for flight status */}
                                <th>메뉴</th>
                            </tr>
                        </thead>
                        <tbody>
                            {flightList.map((flight) => (
                                <tr key={flight.flight_id}>
                                    <td>{flight.flightNumber}</td>
                                    <td>{flight.departureAirport}</td>
                                    <td>{flight.arrivalAirport}</td>
                                    <td>{flight.departureTime}</td>
                                    <td>{flight.arrivalTime}</td>
                                    <td>{flight.flightTotalSeat}</td>
                                    <td>{flight.flight_status}</td> {/* Display flight status */}
                                    <td>
                                        <FaTrash className="text-danger" onClick={() => deleteFlight(flight)} />
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                        <tfoot>
                            <tr>
                                <td>
                                    <input type="text" className="form-control"
                                           placeholder="항공편 번호"
                                           name="flightNumber"
                                           value={input.flightNumber}
                                           onChange={changeInput} />
                                </td>
                                <td>
                                    <input type="text" className="form-control"
                                           placeholder="출발 공항"
                                           name="departureAirport"
                                           value={input.departureAirport}
                                           onChange={changeInput} />
                                </td>
                                <td>
                                    <input type="text" className="form-control"
                                           placeholder="도착 공항"
                                           name="arrivalAirport"
                                           value={input.arrivalAirport}
                                           onChange={changeInput} />
                                </td>
                                <td>
                                    <input type="datetime-local" className="form-control"
                                           name="departureTime"
                                           value={input.departureTime}
                                           onChange={changeInput} />
                                </td>
                                <td>
                                    <input type="datetime-local" className="form-control"
                                           name="arrivalTime"
                                           value={input.arrivalTime}
                                           onChange={changeInput} />
                                </td>
                                <td>
                                    <input type="number" className="form-control"
                                           placeholder="총 좌석 수"
                                           name="flightTotalSeat"
                                           value={input.flightTotalSeat}
                                           onChange={changeInput} />
                                </td>
                                <td>
                                    <select className="form-control"
                                            name="flightStatus"
                                            value={input.flightStatus}
                                            onChange={changeInput}>
                                        <option value="대기">대기</option>
                                        <option value="승인">승인</option>
                                        <option value="반려">반려</option>
                                    </select>
                                </td>
                                <td>
                                    <button type="button"
                                            className="btn btn-success"
                                            onClick={addInput}>
                                        등록
                                    </button>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </>
    );
};

export default Flight;
