import React from 'react';
import { FaFacebookF, FaTwitter, FaLinkedinIn } from 'react-icons/fa';
import { MdEmail, MdLock } from 'react-icons/md';
import './Login.css'; // 스타일을 위한 CSS 파일

const LoginForm = () => {
  return (
      <div className="container-fluid h-custom">
        <div className="row d-flex justify-content-center align-items-center h-100">
          <div className="col-md-9 col-lg-6 col-xl-5">
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

            <div className="form-outline mb-3">
              <input 
                type="email" 
                id="formId" 
                className="form-control form-control-lg" 
                placeholder="Enter a valid email address" 
                required 
              />
              <label className="form-label" htmlFor="formId">
                <MdEmail /> 아이디
              </label>
            </div>

            <div className="form-outline mb-3">
              <input 
                type="password" 
                id="formPassword" 
                className="form-control form-control-lg" 
                placeholder="Enter password" 
                required 
              />
              <label className="form-label" htmlFor="formPassword">
                <MdLock /> 비밀번호
              </label>
            </div>

            <div className="d-flex justify-content-between align-items-center mb-3">
              <div className="form-check mb-0">
                <input 
                  className="form-check-input me-2" 
                  type="checkbox" 
                  id="form2Example3" 
                />
                <label className="form-check-label" htmlFor="form2Example3">Remember me</label>
              </div>
              <a href="#!" className="text-body">Forgot password?</a>
            </div>

            <div className="text-center text-lg-start mt-4 pt-2 mb-0">
              <button type="button" className="btn btn-primary btn-lg" style={{ paddingLeft: '2.5rem', paddingRight: '2.5rem' }}>로그인</button>
              <p className="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="#!" className="link-danger">Register</a></p>
            </div>
          </div>
        </div>
      </div>
  );
}

export default LoginForm;
