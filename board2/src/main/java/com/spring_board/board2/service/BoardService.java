package com.spring_board.board2.service;


import com.spring_board.board2.dto.BoardDto;
import com.spring_board.board2.entity.Board;
import com.spring_board.board2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardDto boardDto;

    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception {

        if(file != null) {
            String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

            UUID uuid = UUID.randomUUID();

            String fileName = uuid + "_" + file.getOriginalFilename();

            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            board.setFilename(fileName);
            board.setFilepath("/files/" + fileName);
        }

        boardRepository.save(board);
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    // id값으로 특정 게시글 불러오기
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    public Page<Board> boardSearchList (String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    //특정 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }



    public BoardDto getBoardInfo(int id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();

            boardDto.setTitle(board.getTitle());
            boardDto.setContent(board.getContent());

            return boardDto;
        } else {
            // 해당 ID에 해당하는 게시글이 없을 경우 처리
            throw new BoardNotFoundException("게시글을 찾을 수 없습니다. ID: " + id);
        }
    }

    public class BoardNotFoundException extends RuntimeException {

        public BoardNotFoundException(String message) {
            super(message);
        }
    }

    public void updateBoard(int id, BoardDto updatedBoardDto) {
        Optional<Board> optionalBoard = boardRepository.findById(id);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.setTitle(updatedBoardDto.getTitle());
            board.setContent(updatedBoardDto.getContent());

            boardRepository.save(board);
        } else {
            throw new BoardNotFoundException("게시글을 찾을 수 없습니다. ID: " + id);
        }
    }

    public void deleteBoard(int id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);

        if (optionalBoard.isPresent()) {
            boardRepository.deleteById(id);
        } else {
            throw new BoardNotFoundException("Post not found. ID: " + id);
        }
    }

    public void createBoard(BoardDto newBoardDto) {
        Board newBoard = new Board();
        newBoard.setTitle(newBoardDto.getTitle());
        newBoard.setContent(newBoardDto.getContent());
        // Set other required fields

        boardRepository.save(newBoard);
    }

}
