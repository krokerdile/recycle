import AskLoginModal from '@components/block/modal/AskLoginModal';
import styled from 'styled-components';

const CenterModalBox = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const TestPageThree = () => {
  return (
    <>
      <CenterModalBox>
        <AskLoginModal />
      </CenterModalBox>
    </>
  );
};

export default TestPageThree;