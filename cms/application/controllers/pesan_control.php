<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Pesan_control extends CI_Controller {


	function __construct()
	{
		parent::__construct();
		// $this->load->model('user_model');
		// $this->load->model('bencana_model');
		// $this->load->model('posko_model');
		$this->load->model('pesan_model');
		$this->load->model('sms_inject');
		$this->load->helper('array');
		$this->load->helper('date');
		$this->load->helper('url');
	}
   
	public function index()
	{
		if($this->session->userdata('logged_in'))
   		{	
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
			$data['base_url'] = $this->config->base_url();

			$this->load->view('pesan_masuk',$data);
		}
		else 
		{
			redirect('login_control/index','refresh');
		}
	}

	public function view_pesan_terkirim()
	{
		// if($this->session->userdata('logged_in'))
   		// {	
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

			$data['pesan_terkirim'] = $this->pesan_model->show_pesan_terkirim();
			
			$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_pegawai";
			$json = file_get_contents($json_url);
			$json=str_replace('},

			]',"}

			]",$json);
			$data['daftar_petugas'] = json_decode($json);
			// $data['daftar_petugas'] = $this->user_model->show();

			$data['username'] = $this->session->userdata('username');

			$this->load->view('pesan_terkirim',$data);
		// }
		// else
		// {
			// redirect('login_control/index','refresh');
		// }
	}

	public function view_broadcast_bencana()
	{
		// if($this->session->userdata('logged_in'))
   		// {	
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

			$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_pegawai_relawan";
			$json = file_get_contents($json_url);
			$json=str_replace('},

			]',"}

			]",$json);
			$data['daftar_petugas'] = json_decode($json);
			// $data['daftar_petugas'] = $this->user_model->show();

			$data['username'] = $this->session->userdata('username');

			$this->load->view('broadcast_info_bencana_pilih_petugas', $data);
		// }
		// else
		// {
		// 	redirect('login_control/index','refresh');
		// }
	}

	public function broadcast_bencana_pilih_petugas()
	{
		if(!empty($this->session->userdata('id_petugas_broadcast_bencana'))) //Reset pilih bencana
		{
			$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_bencana";
			$json = file_get_contents($json_url);
			$json=str_replace('},

			]',"}

			]",$json);
			$data['data_bencana'] = json_decode($json);
			// $data['data_bencana'] = $this->bencana_model->show();

			$data['username'] = $this->session->userdata('username');

			$this->load->view('broadcast_info_bencana_pilih_bencana', $data);
		}
		else
		{
			$value = isset($_POST['petugas']) ? $_POST['petugas'] : '';
			if(!empty($value)) {

				$id_petugas = $this->input->post('petugas');
				$this->session->set_userdata('id_petugas_broadcast_bencana', $id_petugas);
				
				$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_bencana";
				$json = file_get_contents($json_url);
				$json=str_replace('},

				]',"}

				]",$json);
				$data['data_bencana'] = json_decode($json);
				// $data['data_bencana'] = $this->bencana_model->show();

				$data['username'] = $this->session->userdata('username');

				$this->load->view('broadcast_info_bencana_pilih_bencana', $data);
			}
			else
			{
				echo "<script>
				alert('Tolong pilih setidaknya satu pilihan');
				</script>";

				redirect('pesan_control/view_broadcast_bencana','refresh');
			}
		}
	}

	public function broadcast_bencana_pilih_bencana()
	{
		$value = isset($_POST['bencana']) ? $_POST['bencana'] : '';
		if(!empty($value)) 
		{
			$id_bencana = $this->input->post('bencana');
			$this->session->set_userdata('id_bencana_broadcast_bencana', $id_bencana);
			foreach ($value as $selected) 
			{
				$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/search_bencana/".$selected;
				$json = file_get_contents($json_url);
				$json=str_replace('},

				]',"}

				]",$json);
				$data['bencana_dipilih'][] = json_decode($json);
				// $data['bencana_dipilih'][] = 	$this->bencana_model->search_bencana_by_id_bencana($selected);

			}
			
			$id_user = $this->session->userdata('id_petugas_broadcast_bencana');
			foreach ($id_user as $selected) 
			{			
				$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/search_pegawai/".$selected;
				$json = file_get_contents($json_url);
				$json=str_replace('},

				]',"}

				]",$json);
				$data['petugas_dipilih'][] = json_decode($json);
				// $data['petugas_dipilih'][] = $this->user_model->search_by($selected);

			}
			$data['username'] = $this->session->userdata('username');

			$this->load->view('broadcast_info_bencana_kirim', $data);
		}
		else
		{
			echo "<script>
			alert('Tolong pilih setidaknya satu pilihan');
			</script>";
			
			redirect('pesan_control/broadcast_bencana_pilih_petugas','refresh');
		}
	}

	public function broadcast_bencana()
	{
		$id_bencana = $this->session->userdata('id_bencana_broadcast_bencana');
		$id_user = $this->session->userdata('id_petugas_broadcast_bencana');

		foreach ($id_bencana as $rows) 
		{
			foreach ($id_user as $rows2)
			{
				$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/search_no_hp_pegawai/".$rows2;
				$json = file_get_contents($json_url);
				$json=str_replace('},

				]',"}

				]",$json);

				$no_hp = json_decode($json);
				// $no_hp = $this->user_model->search_no_hp_by_id_user($rows2);

				$pesan = "Pesan Broadcast Bencana BPBD. No ID Petugas anda : ".$rows2." - No ID Bencana : ".$rows;

				$db = new PDO('mysql:host=localhost;dbname=smsdb;charset=utf8', 'root', '');
		        $result = $db->exec("INSERT INTO outbox (DestinationNumber,
		        TextDecoded) VALUES ('".$no_hp."', '".$pesan."')");
		        if($result)
		        {
		            echo "<script>alert('Sukses kirim sms ke $no_hp')</script>";
		        }
			}
		}
		$this->session->unset_userdata('id_bencana_broadcast_bencana');
		$this->session->unset_userdata('id_petugas_broadcast_bencana');

		redirect('pesan_control/view_broadcast_bencana','refresh');
	}
	
	public function view_broadcast_posko()
	{
		// if($this->session->userdata('logged_in'))
   		// {	
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
			
			$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_pegawai_relawan";
			$json = file_get_contents($json_url);
			$json=str_replace('},

			]',"}

			]",$json);
			$data['daftar_petugas'] = json_decode($json);
			// $data['daftar_petugas'] = $this->user_model->show();

			$data['username'] = $this->session->userdata('username');

			$this->load->view('broadcast_info_posko_pilih_petugas', $data);
		// }
		// else
		// {
		// 	redirect('login_control/index','refresh');
		// }
	}

	public function view_broadcast_posko_cari_petugas()
	{
		$cari = $this->input->post('cari');

		$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/search_pegawai".$cari;
		$json = file_get_contents($json_url);
		$json=str_replace('},

		]',"}

		]",$json);
		$data['cari_petugas'] = json_decode($json);
		// $data['cari_petugas'] = $this->user_model->search_by($cari);

		$data['username'] = $this->session->userdata('username');

		$this->load->view('broadcast_info_bencana_cari_petugas',$data);
	}	

	public function broadcast_posko_pilih_petugas() //BELOM EDIT
	{
		if(!empty($this->session->userdata('id_petugas_broadcast_posko')))
		{
			$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_posko";
			$json = file_get_contents($json_url);
			$json=str_replace('},

			]',"}

			]",$json);
			$data['data_posko'] = json_decode($json);
			// $data['data_posko'] = $this->posko_model->show();

			$data['username'] = $this->session->userdata('username');

			$this->load->view('broadcast_info_posko_pilih_posko', $data);
		}
		else
		{
			$value = isset($_POST['petugas']) ? $_POST['petugas'] : '';
			if(!empty($value)) {
			
				$id_petugas = $this->input->post('petugas');
				$this->session->set_userdata('id_petugas_broadcast_posko', $id_petugas);
				
				$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_posko";
				$json = file_get_contents($json_url);
				$json=str_replace('},

				]',"}

				]",$json);
				$data['data_posko'] = json_decode($json);
				// $data['data_posko'] = $this->posko_model->show();/

				$data['username'] = $this->session->userdata('username');

				$this->load->view('broadcast_info_posko_pilih_posko', $data);
			}
			else
			{
				echo "<script>
				alert('Tolong pilih setidaknya satu pilihan');
				</script>";

				redirect('pesan_control/view_broadcast_posko','refresh');
			}
		}
	}

	public function broadcast_posko_pilih_posko() //BELOM EDIT
	{
		$value = isset($_POST['posko']) ? $_POST['posko'] : '';
		if(!empty($value)) 
		{
			$id_posko = $this->input->post('posko');
			$this->session->set_userdata('id_posko_broadcast_posko', $id_posko);
			foreach ($value as $selected) 
			{
				$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/search_posko/".$selected;
				$json = file_get_contents($json_url);
				$json=str_replace('},

				]',"}

				]",$json);
				$data['posko_dipilih'][] = json_decode($json);
				// $data['posko_dipilih'][] = $this->posko_model->search_posko_by_id_posko($selected);

			}
			
			$id_user = $this->session->userdata('id_petugas_broadcast_posko');
			foreach ($id_user as $selected) 
			{			

				$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/search_pegawai/".$selected;
				$json = file_get_contents($json_url);
				$json=str_replace('},

				]',"}

				]",$json);
				$data['petugas_dipilih'][] = json_decode($json);
				// $data['petugas_dipilih'][] = $this->user_model->search_by($selected);

			}
			$data['username'] = $this->session->userdata('username');
			
			$this->load->view('broadcast_info_posko_kirim', $data);
		}
		else
		{
			echo "<script>
			alert('Tolong pilih setidaknya satu pilihan');
			</script>";

			redirect('pesan_control/broadcast_posko_pilih_petugas','refresh');
		}
	}

	public function broadcast_posko()//BELOM EDIT
	{
		$id_posko = $this->session->userdata('id_posko_broadcast_posko');
		$id_user = $this->session->userdata('id_petugas_broadcast_posko');

		foreach ($id_posko as $rows) 
		{
			foreach ($id_user as $rows2)
			{
				$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/search_no_hp_pegawai/".$rows2;
				$json = file_get_contents($json_url);
				$json=str_replace('},

				]',"}

				]",$json);

				$no_hp = json_decode($json);
				// $no_hp = $this->user_model->search_no_hp_by_id_user($rows2);

				$pesan = "Pesan Broadcast Posko BPBD. No ID Petugas anda : ".$rows2." - No ID Posko : ".$rows;

				$db = new PDO('mysql:host=localhost;dbname=smsdb;charset=utf8', 'root', '');
		        $result = $db->exec("INSERT INTO outbox (DestinationNumber,
		        TextDecoded) VALUES ('".$no_hp."', '".$pesan."')");
		        if($result)
		        {
		            echo "<script>alert('Sukses kirim sms ke $no_hp')</script>";
		        }
			}
		}
		$this->session->unset_userdata('id_bencana_broadcast_posko');
		$this->session->unset_userdata('id_petugas_broadcast_posko');
		redirect('pesan_control/view_broadcast_posko','refresh');
	}

	public function view_tulis_pesan()
	{
		// if($this->session->userdata('logged_in'))
   		// {	
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
			
			$data['username'] = $this->session->userdata('username');
			$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_pegawai";
			$json = file_get_contents($json_url);
			$json=str_replace('},

			]',"}

			]",$json);
			$data['daftar_petugas'] = json_decode($json);
			// $data['daftar_petugas'] = $this->user_model->show();

			$this->load->view('tulis_pesan', $data);
		// }
		// else
		// {
		// 	redirect('login_control/index','refresh');
		// }
	}

	public function kirim_pesan()
	{
		$isipesan = $this->input->post('isi_pesan');
		$nomor = "+62".$this->input->post('nomor_telepon');
		$petugas = $this->input->post('petugas');

		$this->load->library('form_validation');
		$this->form_validation->set_rules('nomor_telepon', 'Nomor Telepon', 'trim|xss_clean|integer|callback_check_inputan_tujuan|callback_check_panjang_no_telepon');
		$this->form_validation->set_rules('isi_pesan', 'Isi Pesan', 'trim|required|xss_clean');

		$json_url = "http://bpbd2015.esy.es/admin/index.php/web_service/show_all_pegawai";
		$json = file_get_contents($json_url);
		$json=str_replace('},

		]',"}

		]",$json);
		$data['daftar_petugas'] = json_decode($json);
		// $data['daftar_petugas'] = $this->user_model->show();
		
		$data['username'] = $this->session->userdata('username');

		if($this->form_validation->run() == FALSE) // FORM MASIH ERROR
		{
			echo "<script>
				alert('Terjadi Kesalahan!');
				</script>";
			$this->load->view('tulis_pesan',$data);
		}
		else
		{
			if(!empty($this->input->post('nomor_telepon')))
			{
				// $db = new PDO('mysql:host=localhost;dbname=smsdb;charset=utf8', 'root', '');
		  //       $result = $db->exec("INSERT INTO outbox (DestinationNumber,
		  //       TextDecoded) VALUES ('".$nomor."', '".$isipesan."')");
		  //       if($result)
		  //       {
		  //           echo "<script>alert('Sukses kirim sms ke $nomor')</script>";
		  //       }

				$connection = mysqli_connect('localhost', 'root', '', 'smsdb');

				$sms = new sms_inject($connection); 
				$sms->mass_sms($isipesan, $nomor); 
				echo "<script>alert('Sukses kirim sms ke $nomor')</script>";
			}

			if(!empty($petugas))
			{
				foreach ($petugas as $nomorpetugas)
				{
					// var_dump($nomorpetugas);
					// $db = new PDO('mysql:host=localhost;dbname=smsdb;charset=utf8', 'root', '');
			  //       $result = $db->exec("INSERT INTO outbox (DestinationNumber,
			  //       TextDecoded) VALUES ('".$nomorpetugas."', '".$isipesan."')");
			  //       if($result)
			  //       {
			  //           echo "<script>alert('Sukses kirim sms ke $nomorpetugas')</script>";
			  //       }

			        $connection = mysqli_connect('localhost', 'root', '', 'smsdb');

					$sms = new sms_inject($connection); 
					$sms->mass_sms($isipesan, $nomorpetugas); 
					echo "<script>alert('Sukses kirim sms ke $nomorpetugas')</script>";
				}
			}

			redirect('pesan_control/view_tulis_pesan','refresh');
		}
	}

	public function check_inputan_tujuan()
	{
		if(empty($this->input->post('nomor_telepon')) && empty($this->input->post('petugas'))) //Tujuan tidak diisi
		{
			$this->form_validation->set_message('check_inputan_tujuan', 'Isi nomor telepon tujuan atau pilih petugas tujuan');
	     	return false;
		}
		else //Tujuan diisi
		{
			return true;
		}
	}

	public function check_panjang_no_telepon()
 	{
	   //Field validation succeeded.  Validate against database
   		$nomor_telepon = "+62".$this->input->post('nomor_telepon');
   		if(strlen($nomor_telepon) < 16 && strlen($nomor_telepon) > 11)
   		{
   			$result = true;
   		}
   		else
   		{
   			$result = false;
   		}
	   
		if($result)
   		{
	     	return TRUE;
	   	}
	   	else
	   	{
	     	$this->form_validation->set_message('check_panjang_no_telepon', 'Panjang nomor telepon tidak valid');
	     	return false;
	   	}
 	}

}