import React, { useCallback, useState } from 'react';
import axios from 'axios'; // Axios import
import { FaFacebookF, FaTwitter, FaLinkedinIn } from 'react-icons/fa';
import { MdEmail, MdLock } from 'react-icons/md';
import './Login.css'; // 스타일을 위한 CSS 파일
import { NavLink } from 'react-router-dom';

const Login = () => {
    // 상태 관리
    const [loginData, setLoginData] = useState({
        usersId: '',
        usersPw: '',
        rememberMe: false,
    });
    const [isModalOpen, setModalOpen] = useState(false); // 모달 상태 관리

    // 입력값 변경 핸들러
    const InputChange = (e) => {
        const { name, value, type, checked } = e.target;
        setLoginData({
            ...loginData,
            [name]: type === 'checkbox' ? checked : value,
        });
    };

    // 로그인 요청 함수
    const Login = useCallback(async () => {
        try {
            const response = await axios.post('http://localhost:8080/users/login', loginData); // 로그인 API 엔드포인트에 POST 요청
            console.log('로그인 성공:', response.data);
        } catch (error) {
            console.error('로그인 오류:', error);
        }
    }, [loginData]); // loginData가 변경될 때만 Login 함수가 재생성됨

    // 키다운 핸들러
    const handleKeyDown = (e) => {
        if (e.key === 'Enter' && loginData.usersId && loginData.usersPw) {
            Login(); // Enter 키를 누르고 두 입력값이 모두 존재할 때 로그인 요청
        }
    };

    // 모달 열기/닫기 핸들러
    const toggleModal = () => {
        setModalOpen(!isModalOpen);
    };

    // JSX
    return (
        <>
            <div className="container-fluid h-custom mt-5">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-md-9 col-lg-6 col-xl-5">
                        {/* 샘플 이미지 */}
                        <img
                            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                            className="img-fluid"
                            alt="Sample"
                        />
                    </div>
                    <div className="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                        <div className="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                            <p className="lead fw-normal mb-0 me-3">Sign in with</p>
                            <button type="button" className="btn btn-primary btn-floating mx-1">
                                <FaFacebookF />
                            </button>
                            <button type="button" className="btn btn-primary btn-floating mx-1">
                                <FaTwitter />
                            </button>
                            <button type="button" className="btn btn-primary btn-floating mx-1">
                                <FaLinkedinIn />
                            </button>
                        </div>

                        <div className="divider d-flex align-items-center my-4">
                            <p className="text-center fw-bold mx-3 mb-0">Or</p>
                        </div>

                        <div className="form-floating form-outline mb-3">
                            <input
                                name="usersId"
                                type="text"
                                id="formId"
                                className="form-control form-control-lg"
                                placeholder="아이디를 입력하세요"
                                value={loginData.usersId} // 입력값 바인딩
                                onChange={InputChange} // 입력값 변경 시 상태 업데이트
                                onKeyDown={handleKeyDown} // Enter 키 핸들러
                                required
                            />
                            <label className="form-label" htmlFor="formId">
                                <MdEmail /> 아이디
                            </label>
                        </div>

                        <div className="form-floating form-outline mb-3">
                            <input
                                name="usersPw"
                                type="password"
                                id="formPassword"
                                className="form-control form-control-lg"
                                placeholder="비밀번호를 입력하세요"
                                value={loginData.usersPw} // 입력값 바인딩
                                onChange={InputChange} // 입력값 변경 시 상태 업데이트
                                onKeyDown={handleKeyDown} // Enter 키 핸들러
                                required
                            />
                            <label className="form-label" htmlFor="formPassword">
                                <MdLock /> 비밀번호
                            </label>
                        </div>

                        <div className="d-flex justify-content-between align-items-center mb-3">
                            <div className="form-check mb-0">
                                <input
                                    name="rememberMe"
                                    className="form-check-input me-2"
                                    type="checkbox"
                                    id="formRemember"
                                    checked={loginData.rememberMe} // 체크박스 상태 바인딩
                                    onChange={InputChange} // 체크박스 변경 시 상태 업데이트
                                />
                                <label className="form-check-label" htmlFor="formRemember">Remember me</label>
                            </div>
                            <a href="#!" className="text-body">Forgot password?</a>
                        </div>

                        <div className="text-center text-lg-start mt-4 pt-2 mb-0">
                            <button
                                type="button"
                                className="btn btn-primary btn-lg"
                                style={{ paddingLeft: '2.5rem', paddingRight: '2.5rem' }}
                                onClick={Login} // 버튼 클릭 시 로그인 요청
                            >
                                로그인
                            </button>
                            <p className="small fw-bold mt-2 pt-1 mb-0">
                                Don't have an account? <NavLink className="link-danger" onClick={(e) => {
                                    e.preventDefault(); // 기본 동작 방지
                                    toggleModal(); // 모달 열기
                                }}>Register</NavLink>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            {/* 모달 */}
            {isModalOpen && (
                <div className="modal" style={{ display: 'block' }}>
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title">Register</h5>
                                <button type="button" className="close" onClick={toggleModal}>
                                    &times;
                                </button>
                            </div>
                            <div className="modal-body">
                                <p>회원가입 양식</p>
                                {/* 여기에 회원가입 폼을 추가하세요 */}
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" onClick={toggleModal}>닫기</button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
};

export default Login;
