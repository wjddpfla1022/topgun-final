import {atom, selector} from "recoil";

//예제
const countState = atom({
    key: "countState", //식별자(ID)
    default: 0,//초기값
});
export {countState};

//로그인 상태 - 유저ID, 유저 타입
const userState = atom({
    key : 'userState', // 아톰의 고유 ID
    default : {
        userId : '', // 사용자 ID
        userType : '' // 사용자 역할 (ex : MEMBER, ADMIN, AIRLINE)
    }
});

export {userState};