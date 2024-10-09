import {atom, selector} from "recoil";

//예제
const countState = atom({
    key: "countState", //식별자(ID)
    default: 0,//초기값
});
export {countState};