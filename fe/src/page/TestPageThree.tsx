import AskLoginModal from '@components/block/modal/AskLoginModal';
import LockModal from '@components/block/modal/LockModal';
import RegisterEmailModal from '@components/block/modal/RegisterEmailModal';
import SaveModal from '@components/block/modal/SaveModal';
import SharingModal from '@components/block/modal/SharingModal';
import ShowReviewerModal from '@components/block/modal/ShowReviewerModal';
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
        {/* <AskLoginModal /> */}
        {/* <SaveModal /> */}
        {/* <ShowReviewerModal /> */}
        {/* <SharingModal /> */}
        {/* <LockModal /> */}
        <RegisterEmailModal />
      </CenterModalBox>
    </>
  );
};

export default TestPageThree;
