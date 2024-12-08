import useTabStore, { DefaultTabType } from '@store/useTabStore';
import DefaultTab from '../navbar/DefaultTab';
import styled from 'styled-components';
import SearchInput from '../Search/SearchInput';
import { useEffect, useState } from 'react';
import ReviewCard, { ReviewCardProps } from '@components/atom/card/ReviewCard';
import useGetReviews from '@hooks/query/question/useGetReviews';
import useGetReviewOnQuestionDraft from '@hooks/query/question/useGetReviewOnQuestionDraft';
import { useNavigate } from 'react-router-dom';

const Review = () => {
  const items: Record<string, DefaultTabType> = {
    '내가 작성한 리뷰': 'myReview',
    '임시 저장된 리뷰': 'reviewDrafts',
  };
  const { setDefaultTabType } = useTabStore();
  useEffect(() => {
    setDefaultTabType('myReview');
  }, [setDefaultTabType]);

  const { data: ReviewData, isLoading } = useGetReviews();

  const [reviewArray, setReviewArray] = useState([]);
  const [reviewDraftArray, setReviewDraftArray] = useState([]);
  useEffect(() => {
    console.log(ReviewData);
    setReviewArray(ReviewData?.data?.data?.content);
  }, [isLoading, ReviewData]);

  const { data: ReviewDraftData, isLoading: isDraftLoading } = useGetReviewOnQuestionDraft({
    questionId: '1',
    tId: 1,
  });

  const navigate = useNavigate();
  useEffect(() => {
    setReviewDraftArray(ReviewDraftData?.data);
    console.log(reviewDraftArray);
  }, [isDraftLoading]);

  return (
    <BoxWrapper>
      <DefaultTab items={items} />
      <SearchInput />
      <ReviewWrapper>
        {!isLoading &&
          reviewArray &&
          reviewArray.map((item, idx) => {
            return (
              <ReviewCard
                key={idx}
                reviews={item.reviews?.map((item) => item.content)}
                type={'review'}
                commentCount={item.question?.reviewCnt}
                title={'title'}
                onClick={() => {
                  navigate(`/review/${item.question?.questionId}`);
                }}
              />
            );
          })}
      </ReviewWrapper>
    </BoxWrapper>
  );
};

export default Review;

const BoxWrapper = styled.div`
  width: 100%;
`;

const ReviewWrapper = styled.div`
  width: 100%;
  margin-top: 1.5rem;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 0.5rem;
`;
