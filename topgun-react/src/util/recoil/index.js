import { atom, selector } from "recoil";

//예제
const countState = atom({
    key: "countState", //식별자(ID)
    default: 0,//초기값
});
export { countState };

//로그인 상태 - 유저ID, 유저 타입
const userState = atom({
    key: 'userState', // 아톰의 고유 ID
    default: {
        userId: '', // 사용자 ID
        userType: '' // 사용자 역할 (ex : MEMBER, ADMIN, AIRLINE)
    }
});

export { userState };

const loginState = selector({
    key: "loginState",//식별자
    get: (state) => {//state에서 원하는 항목을 읽어서 계산한 뒤 반환

        // userState 아톰에서 userId와 userType을 가져오기
        const user = state.get(userState);

        // userId와 userType이 모두 존재하는지 확인
        // user가 정의되어 있고 userId와 userType이 모두 존재하는지 확인
        return user && user.userId && user.userType &&
            user.userId.length > 0 && user.userType.length > 0;
    }
});
export { loginState };
