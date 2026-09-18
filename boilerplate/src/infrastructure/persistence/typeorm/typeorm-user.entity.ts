import { Column, CreateDateColumn, Entity, PrimaryColumn } from 'typeorm';

@Entity('users')
export class TypeOrmUser {
  @PrimaryColumn()
  id: string;

  @Column({ unique: true })
  email: string;

  @Column({ type: 'varchar', nullable: true })
  username: string | null;

  @Column()
  status: string;

  @CreateDateColumn()
  createdAt: Date;
}