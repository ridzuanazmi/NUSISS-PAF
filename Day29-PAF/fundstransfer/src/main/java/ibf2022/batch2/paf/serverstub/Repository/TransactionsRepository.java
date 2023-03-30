package ibf2022.batch2.paf.serverstub.Repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import ibf2022.batch2.paf.serverstub.Model.Transactions;

public interface TransactionsRepository extends MongoRepository<Transactions, UUID> {
}