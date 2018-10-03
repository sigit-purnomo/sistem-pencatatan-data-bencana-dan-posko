<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Login_control extends CI_Controller {


	function __construct()
	{
		parent::__construct();
		$this->load->helper(array('form'));
	   	$this->load->model('user_model');
	   	$this->load->model('pesan_model');

	}
	   
	public function index()
	{
		if($this->session->userdata('logged_in'))
   		{	
			// $data['daftar_petugas'] = $this->user_model->show();
			// $data['username'] = $this->session->userdata('username');

			// $this->load->view('petugas',$data);
			if($this->session->userdata('id_bencana_broadcast_bencana')) 
			{
			 	$this->session->unset_userdata('id_bencana_broadcast_bencana');
			}
			if($this->session->userdata('id_petugas_broadcast_bencana')) 
			{
				$this->session->unset_userdata('id_petugas_broadcast_bencana');
			}
			if($this->session->userdata('id_bencana_broadcast_posko')) 
			{
				$this->session->unset_userdata('id_bencana_broadcast_posko');
			}
			if($this->session->userdata('id_petugas_broadcast_posko')) 
			{
				$this->session->unset_userdata('id_petugas_broadcast_posko');	
			}

			$data['pesan_masuk'] = $this->pesan_model->show_pesan_masuk();

			$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_pegawai";
			$json = file_get_contents($json_url);
			$json=str_replace('},

			]',"}

			]",$json);
			$data['daftar_petugas'] = json_decode($json);
			// $data['daftar_petugas'] = $this->user_model->show();

			$data['username'] = $this->session->userdata('username');

			$this->load->view('pesan_masuk',$data);

		}
		else 
		{
			$this->load->view('login');
			
		}
	}

	public function login()
	{
		$this->load->library('form_validation');

	  	$this->form_validation->set_rules('username', 'Username', 'trim|required|xss_clean|callback_check_database');
	   	$this->form_validation->set_rules('password', 'Password', 'trim|required|xss_clean');

		if($this->form_validation->run() == FALSE) //LOGIN ERROR
		{
			$this->load->view('login');
		}
		else //LOLOS VALIDASI
		{
			$username= $this->input->post('username');
   			$password = md5($this->input->post('password'));
			
			$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/login3/".$username."/".$password;
			$json = file_get_contents($json_url);
			$json=str_replace('},

			]',"}

			]",$json);
			$newdata = json_decode($json);
      		$this->session->set_userdata($newdata);
			// $this->user_model->login($username, $password);
				
      		if($this->session->userdata('id_bencana_broadcast_bencana')) 
			{
			 	$this->session->unset_userdata('id_bencana_broadcast_bencana');
			}
			if($this->session->userdata('id_petugas_broadcast_bencana')) 
			{
				$this->session->unset_userdata('id_petugas_broadcast_bencana');
			}
			if($this->session->userdata('id_bencana_broadcast_posko')) 
			{
				$this->session->unset_userdata('id_bencana_broadcast_posko');
			}
			if($this->session->userdata('id_petugas_broadcast_posko')) 
			{
				$this->session->unset_userdata('id_petugas_broadcast_posko');	
			}

			$data['pesan_masuk'] = $this->pesan_model->show_pesan_masuk();

			$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_pegawai";
			$json = file_get_contents($json_url);
			$json=str_replace('},

			]',"}

			]",$json);
			$data['daftar_petugas'] = json_decode($json);
			// $data['daftar_petugas'] = $this->user_model->show();

			$data['username'] = $this->session->userdata('username');

			$this->load->view('pesan_masuk',$data);
			// $data['daftar_petugas'] = $this->user_model->show();
			// $data['username'] = $this->session->userdata('username');
			// $this->load->view('petugas', $data);
		}
	}

	public function check_database() //cek sesuaikah username passwordnya
 	{
   		$username = $this->input->post('username');
   		$password = md5($this->input->post('password'));

		$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/login2/".$username."/".$password;
		$json = file_get_contents($json_url);
		$json=str_replace('},

		]',"}

		]",$json);
		$result = json_decode($json);
		// $result = $this->user_model->login2($username, $password);

		if($result)
   		{
	     	return TRUE;
	   	}
	   	else
	   	{
	     	$this->form_validation->set_message('check_database', 'Username atau password salah');
	     	return false;
	   	}
 	}

	public function logout()
	{
		$newdata = array(
                'id_user'    => '',
              	'username'   => '',
              	'role'    => '',
              	'logged_in'   => FALSE
                 );
		$this->session->unset_userdata($newdata);
		$this->session->sess_destroy();
		
		redirect('login_control/index','refresh');
	}
}