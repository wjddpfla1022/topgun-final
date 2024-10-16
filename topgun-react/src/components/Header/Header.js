import { NavLink, useNavigate } from "react-router-dom";

const Header = () => {

    const navigate = useNavigate();

    return (
        <>
            {/* 헤더 */}
            <header className="p-3 bg-dark text-white">
                <div className="container">
                    <div className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                        {/* 브랜딩 텍스트 추가 - 내가볼땐 이미지가 더나을거같음.. */}
                        <span className="d-flex align-items-center mb-lg-0 text-white text-decoration-none fs-4">
                            TopGun
                        </span>
                        <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                            <li>
                                <NavLink to="/" className="nav-link px-2 text-white">
                                    홈
                                </NavLink>
                            </li>
                            <li>
                                <a href="#" className="nav-link px-2 text-white">
                                    Features
                                </a>
                            </li>
                            <li>
                                <a href="#" className="nav-link px-2 text-white">
                                    Pricing
                                </a>
                            </li>
                            <li>
                                <a href="#" className="nav-link px-2 text-white">
                                    FAQs
                                </a>
                            </li>
                            <li>
                                <a href="#" className="nav-link px-2 text-white">
                                    About
                                </a>
                            </li>
                            <li>
                                <a href="/flight" className="nav-link px-2 text-white">
                                    flight
                                </a>
                            </li>
                        </ul>
                        <div className="text-end">
                            <button type="button" onClick={e=>navigate("/login")} className="btn btn-success me-2">
                                로그인
                            </button>
                            <button type="button" onClick={e=>navigate("/join")} className="btn btn-secondary">
                                회원가입
                            </button>
                        </div>
                    </div>
                </div>
            </header>
        </>
    );
};
export default Header;