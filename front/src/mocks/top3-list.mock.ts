import { BoardItem } from "types";

const top3ListMock: BoardItem[] = [
    {
        boardNumber: 2,
        title: '안녕하세요',
        contents: '안녕하세요. 반갑습니다. ',
        imageUrl: 'https://noonnucc-production.sfo2.cdn.digitaloceanspaces.com/202211/1669251843413142.jpeg',
        viewCount: 10,
        commentCount: 15,
        favoriteCount: 5,
        writeDatetime: '2023. 08. 24.',
        nickname: 'easyhoon',
        profileImageUrl: 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Font_Awesome_5_brands_github.svg/1200px-Font_Awesome_5_brands_github.svg.png',
    },
    {
        boardNumber: 3,
        title: '안녕하세요',
        contents: '안녕하세요. 반갑습니다. ',
        imageUrl: 'data:image/png;base64,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',
        viewCount: 10,
        commentCount: 15,
        favoriteCount: 5,
        writeDatetime: '2023. 08. 24.',
        nickname: 'easyhoon',
        profileImageUrl: 'data:image/jpeg;base64,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',
    },
    {
        boardNumber: 4,
        title: '안녕하세요',
        contents: '안녕하세요. 반갑습니다. ',
        imageUrl: 'https://i1.daumcdn.net/thumb/C600x600/?fname=https://img1.daumcdn.net/thumb/R750x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FZTdkG%2Fbtrm3MzKZME%2FUV1H8oADwOWpySPdjEGeE0%2Fimg.jpg',
        viewCount: 10,
        commentCount: 15,
        favoriteCount: 5,
        writeDatetime: '2023. 08. 24.',
        nickname: 'easyhoon',
        profileImageUrl: null,
    }
];

export default top3ListMock;