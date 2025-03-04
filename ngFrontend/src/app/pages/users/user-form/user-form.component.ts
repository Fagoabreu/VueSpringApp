import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../../service/user.service';
import { User } from '../../../model/user';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  imports: [FormsModule],
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.css'
})
export class UserFormComponent {
  user: User;
  private readonly route: ActivatedRoute;
  private readonly router: Router;
  private readonly userService: UserService


  constructor(
    route: ActivatedRoute,
    router: Router,
    userService: UserService) {
    this.route = route;
    this.user = new User();
    this.router = router;
    this.userService = userService;
  }

  onSubmit() {
    this.userService.save(this.user).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }

}
