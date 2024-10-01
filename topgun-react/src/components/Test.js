import React, { useEffect, useState } from 'react';

const Test = () => {
  const [exchangeRates, setExchangeRates] = useState({ inr: null, krw: null });
  const fromCurrency = 'usd';

  const getExchangeRates = async () => {
    const url = `https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/${fromCurrency}.json`;
    try {
      const response = await fetch(url);
      const data = await response.json();
      if (data[fromCurrency]) {
        setExchangeRates({
          inr: data[fromCurrency]['inr'],
          krw: data[fromCurrency]['krw'], // 원화 추가
        });
      } else {
        console.error("환율 데이터가 없습니다.");
      }
    } catch (error) {
      console.error("환율 조회 중 오류 발생:", error);
    }
  };

  useEffect(() => {
    getExchangeRates();
  }, []);

  return (
    <div>
      {exchangeRates.inr && exchangeRates.krw ? (
        <>
          <h1>{`1 ${fromCurrency.toUpperCase()}는 ${exchangeRates.inr} INR입니다.`}</h1>
          <h1>{`1 ${fromCurrency.toUpperCase()}는 ${exchangeRates.krw} KRW입니다.`}</h1>
        </>
      ) : (
        <p>환율을 가져오는 중입니다...</p>
      )}
    </div>
  );
};

export default Test;
