package com.gmw.services.comment.impl;

import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.comment.tos.NewCommentTO;
import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.comment.DBCommentService;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.testutilities.ServiceType;
import com.gmw.services.testutilities.TestDbUtilities;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DBCommentServiceImplTest {

    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        TestDbUtilities.initializeDatabase(ServiceType.COMMENT);
        this.serviceManager = new SqlServiceManager(new SqlRepositoryManager(TestDbUtilities.getConnection()));
    }

    @Test
    public void obtainCommentsByModId() throws ResourceNotFoundException {
        ExistingCommentTO existingCommentTO = serviceManager.getDbCommentService().obtainCommentsByModId(1L).get(0);

        assertNotNull(existingCommentTO);
        assertEquals(Long.valueOf(1), existingCommentTO.getId());
        assertEquals(Long.valueOf(1), existingCommentTO.getModId());
        assertEquals(Long.valueOf(1), existingCommentTO.getUserId());
        assertEquals("That mod is awesome test", existingCommentTO.getComment());
    }

    @Test
    public void obtainUserIdByCommentId() throws ResourceNotFoundException {
        Long userId = serviceManager.getDbCommentService().obtainUserIdByCommentId(1L);

        assertEquals(Long.valueOf(1), userId);
    }

    @Test
    public void createComment() throws ResourceNotCreatedException, ResourceNotFoundException {
        NewCommentTO comment = new NewCommentTO();
        comment.setModId(1L);
        comment.setUserId(1L);
        comment.setComment("Test comment");

        DBCommentService service = serviceManager.getDbCommentService();
        service.createComment(comment);

        ExistingCommentTO actual = service.obtainCommentsByModId(1L).get(1);
        ExistingCommentTO expected = ExistingCommentTO
                .builder()
                .comment("Test comment")
                .id(2L)
                .modId(1L)
                .userId(1L)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void updateComment() throws ResourceNotFoundException, ResourceNotUpdatedException {
        DBCommentService service = serviceManager.getDbCommentService();
        ExistingCommentTO expected = ExistingCommentTO
                .builder()
                .comment("Test comment")
                .id(1L)
                .modId(1L)
                .userId(1L)
                .build();

        service.updateComment(expected);
        ExistingCommentTO actual = service.obtainCommentsByModId(1L).get(0);

        assertEquals(expected, actual);
    }

    @Test
    public void deleteComment() throws ResourceNotFoundException, ResourceNotDeletedException {
        DBCommentService service = serviceManager.getDbCommentService();

        Long id = service.obtainCommentsByModId(1L).get(0).getId();

        assertEquals(Long.valueOf(1), id);

        service.deleteComment(1L);
        List<ExistingCommentTO> actual = service.obtainCommentsByModId(1L);
        assertTrue(actual.isEmpty());
    }
}