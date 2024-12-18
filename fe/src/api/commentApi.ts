import clientApi from './axios';

const commentApi = {
  // 질문 글에 달린 댓글 코멘트 조회 [get]
  getBelowComments: async ({ questionId: question_id }: any) => {
    return await clientApi.common.get(`/questions/${question_id}/comments`);
  },

  // 댓글/대댓글 생성 [post]
  //! @sso9594 URI랑 body 내용 겹치는 거 같은데요...?
  createBelowComments: async ({ questionId: question_id, parentId: parent_id, content }: any) => {
    return await clientApi.common.post(`/questions/${question_id}/comments`, {
      content,
      parent_id,
    });
  },

  // 댓글 수정 [put]
  updateBelowComments: async ({
    questionId: question_id,
    parentId: parent_id,
    content,
    commentId: comment_id,
  }: any) => {
    return await clientApi.common.put(`/questions/${question_id}/comments/${comment_id}`, {
      question_id,
      content,
      parent_id,
    });
  },

  // 댓글 삭제 [delete]
  deleteBelowComments: async ({ questionId: question_id, commentId: comment_id }: any) => {
    return await clientApi.common.delete(`/questions/${question_id}/comments/${comment_id}`);
  },
};

export default commentApi;
