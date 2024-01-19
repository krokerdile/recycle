import SideBar from "@components/block/sideBar/SideBar";
import styled from "styled-components";

const GridTemplate = () => {
  return (
    <LayoutWrapper>
      <div>
        <SideBar />
      </div>
      <MainWrapper>
        <TopHeader>Topbar</TopHeader>
        <MainContent>Main</MainContent>
        <RightContent>RightBar</RightContent>
      </MainWrapper>
    </LayoutWrapper>
  );
};

export default GridTemplate;

const LayoutWrapper = styled.div`
  width: 100vw;
  height: 100vh;
  display: grid;
  grid-template-columns: 2fr 10fr;
  grid-template-areas: "Sidebar";
`;
const MainWrapper = styled.div`
  display: grid;
  grid-template-rows: 1fr 9fr; /* Updated grid-template-rows */
  grid-template-columns: 10fr;
`;

const TopHeader = styled.div`
  grid-column: 1 / span 10;
  grid-row: 1 / 2;
`;

const MainContent = styled.div`
  display: inline;
  grid-column: 2 / span 7;
  grid-row: 2 / 10; /* Updated grid-row */
`;

const RightContent = styled.div`
  grid-column: 2 / span 3; /* Updated grid-column */
  grid-row: 2 / 10; /* Updated grid-row */
`;