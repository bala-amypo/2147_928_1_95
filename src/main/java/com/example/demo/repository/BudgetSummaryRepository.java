import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetSummaryRepository extends JpaRepository<BudgetSummary, Long> {

    Optional<BudgetSummary> findFirstByBudgetPlanOrderByGeneratedAtDesc(BudgetPlan budgetPlan);
}
