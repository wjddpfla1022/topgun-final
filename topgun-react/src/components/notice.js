import { useCallback, useState, useEffect } from "react";
import { FaTrash } from "react-icons/fa";

const NoticeBoard = () => {
    // 샘플 공지사항 데이터
    const sampleNotices = [
        {
            notice_id: 1,
            title: "첫 번째 공지사항",
            content: "첫 번째 공지사항의 내용입니다.",
            author: "관리자",
            createdAt: "2024-10-17T10:00",
            status: "공개"
        },
        {
            notice_id: 2,
            title: "두 번째 공지사항",
            content: "두 번째 공지사항의 내용입니다.",
            author: "관리자",
            createdAt: "2024-10-18T15:00",
            status: "비공개"
        }
    ];

    const [noticeList, setNoticeList] = useState(sampleNotices); // 샘플 데이터 사용
    const [input, setInput] = useState({
        title: "",
        content: "",
        author: "",
        createdAt: new Date().toISOString().slice(0, 16), // 현재 시간
        status: "공개", // Default status
    });

    // 데이터 로딩 함수: 실제 API 호출은 주석 처리
    useEffect(() => {
        // loadList(); // API 호출은 생략
    }, []);

    // 공지사항 삭제
    const deleteNotice = useCallback((target) => {
        const choice = window.confirm("정말 삭제하시겠습니까?");
        if (choice) {
            setNoticeList(prevNotices => prevNotices.filter(notice => notice.notice_id !== target.notice_id));
        }
    }, []);

    // 입력 필드 업데이트
    const changeInput = useCallback(e => {
        const { name, value } = e.target;
        setInput(prevInput => ({
            ...prevInput,
            [name]: value
        }));
    }, []);

    // 공지사항 추가
    const addInput = useCallback(() => {
        const newNotice = {
            notice_id: noticeList.length + 1, // 임시로 ID 생성
            ...input
        };
        setNoticeList(prevNotices => [...prevNotices, newNotice]);
        clearInput(); // 입력 필드 초기화
    }, [input, noticeList]);

    // 입력 필드 초기화
    const clearInput = useCallback(() => {
        setInput({
            title: "",
            content: "",
            author: "",
            createdAt: new Date().toISOString().slice(0, 16),
            status: "공개", // Default reset
        });
    }, []);

    return (
        <div className="row mt-4">
            <div className="col">
                <table className="table" style={{ tableLayout: 'fixed', width: '100%' }}>
                    <thead>
                        <tr>
                            <th style={{ padding: '15px' }}>제목</th>
                            <th style={{ padding: '15px' }}>내용</th>
                            <th style={{ padding: '15px' }}>작성자</th>
                            <th style={{ padding: '15px' }}>작성일</th>
                            <th style={{ padding: '15px' }}>상태</th>
                            <th style={{ padding: '15px' }}>메뉴</th>
                        </tr>
                    </thead>
                    <tbody>
                        {noticeList.map((notice) => (
                            <tr key={notice.notice_id}>
                                <td style={{ padding: '15px' }}>{notice.title}</td>
                                <td style={{ padding: '15px' }}>{notice.content}</td>
                                <td style={{ padding: '15px' }}>{notice.author}</td>
                                <td style={{ padding: '15px' }}>{notice.createdAt}</td>
                                <td style={{ padding: '15px' }}>{notice.status}</td>
                                <td style={{ padding: '15px' }}>
                                    <FaTrash className="text-danger" onClick={() => deleteNotice(notice)} />
                                </td>
                            </tr>
                        ))}
                    </tbody>
                    <tfoot>
                        <tr>
                            <td>
                                <input type="text" className="form-control"
                                       placeholder="제목"
                                       name="title"
                                       value={input.title}
                                       onChange={changeInput} />
                            </td>
                            <td>
                                <input type="text" className="form-control"
                                       placeholder="내용"
                                       name="content"
                                       value={input.content}
                                       onChange={changeInput} />
                            </td>
                            <td>
                                <input type="text" className="form-control"
                                       placeholder="작성자"
                                       name="author"
                                       value={input.author}
                                       onChange={changeInput} />
                            </td>
                            <td>
                                <input type="datetime-local" className="form-control"
                                       name="createdAt"
                                       value={input.createdAt}
                                       onChange={changeInput} />
                            </td>
                            <td>
                                <select className="form-control"
                                        name="status"
                                        value={input.status}
                                        onChange={changeInput}>
                                    <option value="공개">공개</option>
                                    <option value="비공개">비공개</option>
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
    );
};

export default NoticeBoard;